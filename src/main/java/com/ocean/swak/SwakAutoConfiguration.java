package com.ocean.swak;

import com.ocean.swak.config.SwakProperties;
import com.ocean.swak.executor.SwakInit;
import com.ocean.swak.register.DefaultSwakRegister;
import com.ocean.swak.register.SwakRegister;
import com.ocean.swak.route.rule.LookUpRuleComposite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author: junqing.li
 * @date: 2019/9/4
 */
@ComponentScan
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableConfigurationProperties(SwakProperties.class)
@Configurable
public class SwakAutoConfiguration {

    @Autowired
    private SwakProperties swakProperties;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    @ConditionalOnMissingBean
    public SwakRegister swakRegister() {
        SwakRegister swakRegister = new DefaultSwakRegister();
        return swakRegister;
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public SwakInit swakInit(SwakRegister swakRegister) {

        SwakInit swakInit = new SwakInit();
        swakInit.setApplicationContext(applicationContext);
        swakInit.setSwakRegister(swakRegister);
        return swakInit;
    }


}
