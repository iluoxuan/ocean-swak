package com.ocean.swak.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: junqing.li
 * @date: 2019/9/4
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SwakInterface {

    /**
     * 接口功能描述
     *
     * @return
     */
    String desc() default "";

}
