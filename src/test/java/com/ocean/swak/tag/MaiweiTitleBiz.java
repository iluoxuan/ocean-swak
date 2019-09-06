package com.ocean.swak.tag;

import com.ocean.swak.annotation.SwakBiz;
import com.ocean.swak.entity.SwakContext;

/**
 * @author: junqing.li
 * @date: 2019/9/6
 */
@SwakBiz(tags = "maiwei")
public class MaiweiTitleBiz implements TitleBiz {

    @Override
    public String getTitle(String param, SwakContext swakContext) {

        System.out.println("--- mai wei");

        return "maiwei";
    }
}
