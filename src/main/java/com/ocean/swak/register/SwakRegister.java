package com.ocean.swak.register;

import com.ocean.swak.entity.MethodExecuteInfo;

/**
 * @author: junqing.li
 * @date: 2019/9/4
 */
public interface SwakRegister {

    /**
     * 注册 执行信息
     *
     * @param executeInfo
     */
    void register(MethodExecuteInfo executeInfo);

    MethodExecuteInfo lookUp(String key);

    /**
     * clear
     */
    void clear();

}
