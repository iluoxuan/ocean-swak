package com.ocean.swak.service;

import com.ocean.swak.AbstractTest;
import com.ocean.swak.entity.SwakContext;
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


}
