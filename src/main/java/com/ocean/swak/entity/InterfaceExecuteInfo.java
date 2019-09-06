package com.ocean.swak.entity;

import lombok.Data;

/**
 * @author: junqing.li
 * @date: 2019/9/5
 */
@Data
public class InterfaceExecuteInfo extends ExecuteInfo {

    /**
     * 接口执行的bean
     */
    private Object target;
}
