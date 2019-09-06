package com.ocean.swak.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: junqing.li
 * @date: 2019/9/4
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "multiple.swak")
public class SwakProperties {



}
