package com.ocean.swak.annotation;

import com.ocean.swak.config.SwakConstants;
import org.springframework.stereotype.Component;

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
@Component
public @interface SwakBiz {

    /**
     * 业务线
     *
     * @return
     */
    String bizCode() default SwakConstants.swakDefaultBiz;

    /**
     * 要实现的 tag
     *
     * @return
     */
    String[] tags() default {SwakConstants.swakDefaultBiz};

    /**
     * 失败了 跳过
     * @return
     */
    boolean skip() default false;

    /**
     * 和某个tag 同时执行
     * @return
     */
    String[] merge() default "";

    /**
     * 和那几个表情产生冲突
     * @return
     */
    String[] conflict() default "";

    /**
     * 顺序
     * @return
     */
    int order() default 0;

}
