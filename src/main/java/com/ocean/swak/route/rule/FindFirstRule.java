package com.ocean.swak.route.rule;

import com.ocean.swak.annotation.SwakInterface;
import com.ocean.swak.entity.LookUpParam;
import com.ocean.swak.entity.MethodExecuteInfo;
import com.ocean.swak.register.SwakRegister;
import com.ocean.swak.utils.ClassUtils;
import com.ocean.swak.utils.SwakUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.Optional;

/**
 * @author: junqing.li
 * @date: 2019/9/6
 */
@Component
public class FindFirstRule implements LookUpRule {

    @Autowired
    private SwakRegister swakRegister;

    @Override
    public boolean support(LookUpParam param) {

        return LookUpType.FindFirst.equals(param.getLookUpType());
    }

    @Override
    public MethodExecuteInfo lookUp(LookUpParam param) {

        String interfaceName = ClassUtils.getQualifiedNameByAnnotation(param.getTarget().getClass(),
                SwakInterface.class);
        Assert.hasText(interfaceName, "no find @SwakBiz in impl");

        Optional<MethodExecuteInfo> optional = param.getTags().stream().map(tag -> {

            String key = SwakUtils.getCacheKey(param.getMethod(), interfaceName, param.getBizCode(), tag);
            MethodExecuteInfo executeInfo = swakRegister.lookUp(key);
            return executeInfo;
        }).filter(Objects::nonNull).findFirst();

        Assert.isTrue(optional.isPresent(), "no find @SwakBiz in impl");

        return optional.get();
    }
}
