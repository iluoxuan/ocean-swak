package com.ocean.swak.advice;


import com.ocean.swak.annotation.SwakBiz;
import com.ocean.swak.entity.*;
import com.ocean.swak.route.rule.LookUpRuleComposite;
import com.ocean.swak.utils.ClassUtils;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * @author: junqing.li
 * @date: 2019/9/4
 */
@Getter
@Setter
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Aspect
public class SwakInterfaceAop {


    @Autowired
    private LookUpRuleComposite lookUpRuleComposite;

    /**
     * 拦截类上的 注解用 @within
     */
    @Pointcut("@within(com.ocean.swak.annotation.SwakBiz)")
    private void annotationPoint() {

    }


    @Around("annotationPoint()")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Object[] args = joinPoint.getArgs();

        if (ClassUtils.isOriginMethod(method)) {
            return joinPoint.proceed(args);
        }

        try {

            SwakBiz swakBiz = AnnotationUtils.findAnnotation(joinPoint.getThis().getClass(), SwakBiz.class);
            if (Objects.isNull(swakBiz)) {
                return joinPoint.proceed(args);
            }
            SwakContext swakContext = getContext(args);
            // 方法名称 指定执行
            if (ExecuteType.isMethodNameInvoke(swakContext.getExecuteType())) {

                return methodInvoke(joinPoint, method, swakContext);
            }

            return interfaceInvoke(joinPoint, method, swakContext);

        } catch (Throwable e) {
            throw new RuntimeException(e);

        } finally {

            SwakLocal.getCurrent().clear();
        }
    }

    /**
     * 制定方法执行
     *
     * @param joinPoint
     * @param method
     * @param
     * @return
     */
    private Object methodInvoke(ProceedingJoinPoint joinPoint, Method method, SwakContext swakContext)
            throws Throwable {

        return method.invoke(joinPoint.getTarget(), joinPoint.getArgs());
    }


    /**
     * 接口 执行方法
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    private Object interfaceInvoke(ProceedingJoinPoint joinPoint, Method method, SwakContext swakContext) throws Throwable {


        // 寻找的目标执行信息
        LookUpParam lookUpParam = new LookUpParam();
        BeanUtils.copyProperties(swakContext, lookUpParam);
        lookUpParam.setTarget(joinPoint.getTarget());

        MethodExecuteInfo executeInfo = lookUpRuleComposite.lookUp(lookUpParam);
        Assert.notNull(executeInfo, "no find interface execute info");

        // 获取真实对象
        Class<?> clazz = AopUtils.getTargetClass(executeInfo.getTarget());
        // 获得对应的method
        Method executeMethod = ClassUtils.getMethod(clazz, method.getName(), method.getParameterTypes());

        // 判断是否是代理对象
        Object bean = executeInfo.getTarget();
        if (AopUtils.isAopProxy(bean)) {
            bean = AopProxyUtils.getSingletonTarget(executeInfo.getTarget());
        }
        return executeMethod.invoke(bean, joinPoint.getArgs());

    }


    private SwakContext getContext(Object[] args) {

        SwakContext swakContext = getContextByParam(args);
        if (Objects.nonNull(swakContext)) {
            return swakContext;
        }

        // 从local中取
        swakContext = SwakLocal.getCurrent().getContext();
        if (Objects.nonNull(swakContext)) {
            return swakContext;
        }

        Assert.notNull(swakContext, "swak must set swak context");
        return swakContext;
    }

    private SwakContext getContextByParam(Object[] args) {

        if (ObjectUtils.isEmpty(args)) {
            return null;
        }

        Optional<SwakContext> optional = Arrays.stream(args).filter(Objects::nonNull).filter(object -> object.getClass()
                .isAssignableFrom(SwakContext.class)).map(object -> (SwakContext) object).findFirst();
        if (!optional.isPresent()) {
            return null;
        }
        return optional.get();
    }
}
