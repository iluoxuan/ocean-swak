package com.ocean.swak.entity;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author: junqing.li
 * @date: 2019/9/5
 */
@Data
public class MethodExecuteInfo {

    /**
     * 接口执行的bean
     */
    private Object target;

    /**
     * 方法
     */
    private Method method;

    /**
     * 所有的tag
     */
    private List<String> tags;

    /**
     * 业务线
     */
    private String bizCode;
}
