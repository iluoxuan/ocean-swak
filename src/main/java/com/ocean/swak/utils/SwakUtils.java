package com.ocean.swak.utils;

import com.ocean.swak.config.SwakConstants;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author: junqing.li
 * @date: 2019/9/6
 */
public class SwakUtils {

    /**
     * 获取 缓存key
     *
     * @param method
     * @param interfaceName
     * @param bizCode
     * @param tag
     * @return
     */
    public static String getCacheKey(Method method, String interfaceName, String bizCode, String tag) {

        String prefixKeyName = Objects.isNull(method) ? interfaceName :
                interfaceName + "#" + method.getName();

        return String.format(SwakConstants.registerCacheKeyFormat, prefixKeyName, bizCode, tag);
    }
}
