package com.ocean.swak.register;

import com.google.common.collect.Maps;
import com.ocean.swak.annotation.SwakInterface;
import com.ocean.swak.config.SwakConstants;
import com.ocean.swak.entity.InterfaceExecuteInfo;
import com.ocean.swak.entity.MethodExecuteInfo;
import com.ocean.swak.entity.SwakContext;
import com.ocean.swak.utils.ClassUtils;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Optional;

/**
 * 要支持两种 调用方式
 * 1. 接口直接调用
 * <p>
 * 2. 通过方法名称调用
 *
 * @author: junqing.li
 * @date: 2019/9/4
 */
public class DefaultSwakRegister implements SwakRegister {

    private final static String DEFAULT_FORMAT = "%s-%s-%s";

    /**
     * 缓存接口调用
     * channel-tag-interface
     */
    private final static Map<String, InterfaceExecuteInfo> interfaceExecuteCache = Maps.newHashMap();

    /**
     * 缓存更多的信息
     * <p>
     * channel-tag-method [按方法名称调用]
     */
    private final static Map<String, MethodExecuteInfo> methodExecuteCache = Maps.newHashMap();


    @Override
    public void register(InterfaceExecuteInfo executeInfo) {

        Optional<Class<?>> optional = ClassUtils.getInterfaceClassByAnnotation(
                executeInfo.getTarget().getClass(), SwakInterface.class);
        if (!optional.isPresent()) {
            return;
        }

        String interfaceName = ClassUtils.getQualifiedName(optional.get());
        String key = String.format(DEFAULT_FORMAT, interfaceName, executeInfo.getBizCode(), executeInfo.getTags().get(0));

        interfaceExecuteCache.put(key, executeInfo);
    }

    @Override
    public void register(MethodExecuteInfo executeInfo) {


        String key = String.format(DEFAULT_FORMAT, executeInfo.getBizCode(),
                executeInfo.getTags().get(0), executeInfo.getMethod().getName());

        methodExecuteCache.put(key, executeInfo);
    }

    @Override
    public InterfaceExecuteInfo lookUp(InterfaceExecuteInfo selectInfo) {

        Optional<Class<?>> optional = ClassUtils.getInterfaceClassByAnnotation(
                selectInfo.getTarget().getClass(), SwakInterface.class);
        Assert.isTrue(optional.isPresent(), "no find @SwakInterface class");

        String interfaceName = ClassUtils.getQualifiedName(optional.get());
        String key = String.format(DEFAULT_FORMAT, interfaceName, selectInfo.getBizCode(), selectInfo.getTags().get(0));

        return interfaceExecuteCache.get(key);
    }

    @Override
    public MethodExecuteInfo lookMethod(SwakContext swakContext, String methodName) {

        String tag = CollectionUtils.isEmpty(swakContext.getTags()) ? SwakConstants.swakDefaultBiz :
                swakContext.getTags().get(0);

        String key = String.format(DEFAULT_FORMAT, swakContext.getBizCode(),
                tag, methodName);

        return methodExecuteCache.get(key);
    }

    @Override
    public void clear() {

        interfaceExecuteCache.clear();
        methodExecuteCache.clear();

    }
}
