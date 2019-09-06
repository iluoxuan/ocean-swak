package com.ocean.swak.register;

import com.google.common.collect.Maps;
import com.ocean.swak.annotation.SwakInterface;
import com.ocean.swak.entity.MethodExecuteInfo;
import com.ocean.swak.utils.ClassUtils;
import com.ocean.swak.utils.SwakUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

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


    /**
     * 缓存接口调用
     * channel-tag-interface
     */
    private final static Map<String, MethodExecuteInfo> interfaceExecuteCache = Maps.newHashMap();

    /**
     * 缓存更多的信息
     * <p>
     * channel-tag-interface#method [按方法名称调用]
     */
    private final static Map<String, MethodExecuteInfo> methodExecuteCache = Maps.newHashMap();


    @Override
    public void register(MethodExecuteInfo executeInfo) {


        String interfaceName = ClassUtils.getQualifiedNameByAnnotation(executeInfo.getTarget().getClass(),
                SwakInterface.class);
        if (!StringUtils.hasText(interfaceName)) {
            return;
        }

        // 注入 每个标签
        executeInfo.getTags().stream().forEach(tag -> {

            Method method = executeInfo.getMethod();
            String key = SwakUtils.getCacheKey(method, interfaceName, executeInfo.getBizCode(), tag);

            if (Objects.isNull(method)) {
                interfaceExecuteCache.put(key, executeInfo);
            } else {
                methodExecuteCache.put(key, executeInfo);
            }

        });

    }


    @Override
    public MethodExecuteInfo lookUp(String key) {

        return interfaceExecuteCache.get(key);
    }


    @Override
    public void clear() {

        interfaceExecuteCache.clear();
        methodExecuteCache.clear();

    }
}
