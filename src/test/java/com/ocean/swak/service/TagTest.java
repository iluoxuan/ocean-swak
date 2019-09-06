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
        titleBiz.getTitle("xxxx", swakContext);

    }


    /**
     * 测试通过 SwakLocal
     */
    @Test
    public void tagByLocal() {

        SwakLocal.getCurrent().setContext(new SwakContext("maiwei"));
        titleBiz.getTitle("xxxxx", null);
    }

    /**
     * 添加一个 activity 的活动，要在 dc业务上调用的时候 先调用activity
     * 有 --> 返回
     * 无 ---> 再调用dc
     */
    @Test
    public void tagFirst() {

        SwakContext swakContext = new SwakContext("activit", "dc");
        titleBiz.getTitle("xxxx", swakContext);
    }


}
