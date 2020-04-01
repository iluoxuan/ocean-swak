package com.ocean.swak.tag;

import com.ocean.swak.annotation.SwakBiz;
import com.ocean.swak.entity.SwakContext;

/**
 * @author: junqing.li
 * @date: 2019/9/6
 */
@SwakBiz(tags = "activit")
public class ActivitTitileBiz implements TitleBiz {

    @Override
    public String getSuperHero(String param, SwakContext swakContext) {

        System.out.println(" --- activit ----");

        return "activit";
    }
}
