package com.ocean.swak.tag;

import com.ocean.swak.annotation.SwakBiz;
import com.ocean.swak.entity.SwakContext;

/**
 * @author: junqing.li
 * @date: 2019/9/6
 */
@SwakBiz(tags = "dc")
public class DcTtitleBiz implements TitleBiz {

    @Override
    public String getSuperHero(String param, SwakContext swakContext) {

        System.out.println("--- in dc ----");

        // TODO 模拟远程调用
        rpcGet();

        return "dc";
    }

    private void rpcGet() {

        System.out.println(" --- in rpc ---");

        throw new RuntimeException("rpc excetion");
    }
}
