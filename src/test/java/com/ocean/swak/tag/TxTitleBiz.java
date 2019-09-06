package com.ocean.swak.tag;

import com.ocean.swak.annotation.SwakBiz;
import com.ocean.swak.entity.SwakContext;
import org.springframework.context.annotation.Primary;

/**
 * @author: junqing.li
 * @date: 2019/9/6
 */
@Primary
@SwakBiz(tags = "tx")
public class TxTitleBiz implements TitleBiz {

    @Override
    public String getTitle(String param, SwakContext swakContext) {

        System.out.println("--- tx ----");

        return "tx";
    }
}
