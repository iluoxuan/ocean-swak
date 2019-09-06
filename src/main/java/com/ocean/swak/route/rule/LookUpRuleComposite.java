package com.ocean.swak.route.rule;

import com.ocean.swak.entity.LookUpParam;
import com.ocean.swak.entity.MethodExecuteInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: junqing.li
 * @date: 2019/9/6
 */
@Component
public class LookUpRuleComposite {

    @Autowired
    private List<LookUpRule> lookUpRules;


    public MethodExecuteInfo lookUp(LookUpParam param) {

        for (LookUpRule lookUpRule : lookUpRules) {
            if (lookUpRule.support(param)) {
                return lookUpRule.lookUp(param);
            }
        }

        throw new RuntimeException("no find interface execute");
    }
}

