package com.ocean.swak.service;

import com.ocean.swak.AbstractTest;
import com.ocean.swak.entity.SwakContext;
import com.ocean.swak.entity.SwakLocal;
import com.ocean.swak.tag.TitleBiz;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: junqing.li
 * @date: 2019/9/6
 */
public class TagTest extends AbstractTest {


    @Autowired
    private TitleBiz titleBiz;

    /**
     * 测试多实现接口
     */
    @Test
    public void tagInterface() {

        SwakContext swakContext = new SwakContext("dc");
        titleBiz.getSuperHero("xxxx", swakContext);

        // 中间有远程调用这么办

        // 但是我有一个这样的方法。dc中没有找到 我要去漫威中去找 【有一个查找的规则】

    }


    /**
     * 测试通过 SwakLocal
     */
    @Test
    public void tagByLocal() {

        SwakLocal.getCurrent().setContext(new SwakContext("maiwei"));
        titleBiz.getSuperHero("xxxxx", null);
    }

    /**
     * 添加一个 activity 的活动，要在 dc业务上调用的时候 先调用activity
     * 有 --> 返回
     * 无 ---> 再调用dc
     */
    @Test
    public void tagFirst() {

        SwakContext swakContext = new SwakContext("activit", "dc");
        titleBiz.getSuperHero("xxxx", swakContext);
    }


}
