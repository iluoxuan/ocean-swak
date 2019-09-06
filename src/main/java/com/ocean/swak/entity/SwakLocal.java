package com.ocean.swak.entity;


import com.ocean.swak.config.SwakConstants;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 添加上下文副本
 *
 * @author: junqing.li
 * @date: 2019/8/8
 */
public class SwakLocal extends ConcurrentHashMap<String, Object> {

    private static Class<? extends SwakLocal> contextClass = SwakLocal.class;


    private static final ThreadLocal<? extends SwakLocal> threadLocal = ThreadLocal.withInitial(
            () -> {
                try {
                    return contextClass.newInstance();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            });

    public SwakLocal() {
        super();
    }

    public static SwakLocal getCurrent() {
        SwakLocal context = threadLocal.get();
        return context;
    }

    public void clear() {

        threadLocal.remove();
    }


    public <T> T getObject(String key) {

        return (T) get(key);
    }

    public <T> T getObjectOrDefault(String key, T defaultValue) {

        return (T) getOrDefault(key, defaultValue);
    }

    public void set(String key) {
        put(key, Boolean.TRUE);
    }

    /**
     * puts the key, value into the map. a null value will remove the key from the map
     */
    public void set(String key, Object value) {
        if (value != null) {
            put(key, value);
        } else {
            remove(key);
        }
    }

    public SwakContext getContext() {
        return getObject(SwakConstants.swakContext);
    }

    public void setContext(SwakContext swakContext) {
        set(SwakConstants.swakContext, swakContext);
    }

}
