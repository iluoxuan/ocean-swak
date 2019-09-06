package com.ocean.swak.tag;

import com.ocean.swak.annotation.SwakInterface;
import com.ocean.swak.entity.SwakContext;

/**
 * @author: junqing.li
 * @date: 2019/9/6
 */
@SwakInterface(desc = "获取标题")
public interface TitleBiz {

    String getTitle(String param, SwakContext swakContext);
}
