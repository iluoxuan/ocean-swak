package com.ocean.swak.executor;

import com.ocean.swak.annotation.SwakBiz;
import com.ocean.swak.annotation.SwakInterface;
import com.ocean.swak.entity.InterfaceExecuteInfo;
import com.ocean.swak.entity.MethodExecuteInfo;
import com.ocean.swak.register.SwakRegister;
import com.ocean.swak.utils.ClassUtils;
import lombok.Data;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * 初始化
 *
 * @author: junqing.li
 * @date: 2019/9/4
 */
@Data
public class SwakInit {

    private SwakRegister swakRegister;

    private ApplicationContext applicationContext;

    /**
     * 初始化
     */
    public void init() {

        // 找到
        Map<String, Object> swakBizs = applicationContext.getBeansWithAnnotation(SwakInterface.class);
        if (CollectionUtils.isEmpty(swakBizs)) {
            return;
        }

        swakBizs.entrySet().forEach(entry -> {

            Object bean = entry.getValue();
            SwakBiz swakBiz = AnnotationUtils.findAnnotation(bean.getClass(), SwakBiz.class);

            InterfaceExecuteInfo executeInfo = new InterfaceExecuteInfo();
            executeInfo.setTags(Lists.newArrayList(swakBiz.tags()));
            executeInfo.setTarget(bean);
            executeInfo.setBizCode(swakBiz.bizCode());
            swakRegister.register(executeInfo);

            // 再注册method
            Method[] methods = bean.getClass().getDeclaredMethods();
            Arrays.stream(methods).forEach(method -> {

                if (ClassUtils.isOriginMethod(method)) {
                    return;
                }

                MethodExecuteInfo methodExecuteInfo = new MethodExecuteInfo();
                BeanUtils.copyProperties(executeInfo, methodExecuteInfo);
                methodExecuteInfo.setMethod(method);
                methodExecuteInfo.setTarget(bean);

                swakRegister.register(methodExecuteInfo);

            });


        });

    }

    public void destroy() {
        swakRegister.clear();
    }
}
