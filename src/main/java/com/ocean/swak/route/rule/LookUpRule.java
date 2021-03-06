package com.ocean.swak.route.rule;

import com.ocean.swak.entity.LookUpParam;
import com.ocean.swak.entity.MethodExecuteInfo;

/**
 * 查找规则
 *
 * @author: junqing.li
 * @date: 2019/9/6
 */
public interface LookUpRule {

    boolean support(LookUpParam param);

    /**
     * @param param
     * @return
     */
    MethodExecuteInfo lookUp(LookUpParam param);


}
