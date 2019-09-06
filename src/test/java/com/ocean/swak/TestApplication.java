package com.ocean.swak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: junqing.li
 * @date: 2019/9/6
 */
@ImportAutoConfiguration(SwakAutoConfiguration.class)
@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }


}
