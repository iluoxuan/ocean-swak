package com.ocean.swak.entity;

/**
 * @author: junqing.li
 * @date: 2019/9/5
 */
public enum ExecuteType {

    /**
     * 接口主动执行
     */
    interfaceInvoke,

    /**
     * 方法名制定
     */
    methodNameInvoke,;


    public static boolean isMethodNameInvoke(ExecuteType executeType) {

        return ExecuteType.methodNameInvoke.equals(executeType);
    }
}
