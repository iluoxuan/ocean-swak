package com.ocean.swak.entity;

import com.ocean.swak.route.rule.LookUpType;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author: junqing.li
 * @date: 2019/9/6
 */
@Data
public class LookUpParam {

    /**
     * 所有的tag
     */
    private List<String> tags;

    /**
     * 业务线
     */
    private String bizCode;

    private Object target;

    private Method method;

    /**
     * 默认策略
     */
    private LookUpType lookUpType = LookUpType.FindFirst;


}
