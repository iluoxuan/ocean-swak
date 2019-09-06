package com.ocean.swak.entity;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author: junqing.li
 * @date: 2019/9/5
 */
@Data
public class MethodExecuteInfo extends ExecuteInfo {

    /**
     * 接口执行的bean
     */
    private Object target;

    /**
     * 方法
     */
    private Method method;
}
