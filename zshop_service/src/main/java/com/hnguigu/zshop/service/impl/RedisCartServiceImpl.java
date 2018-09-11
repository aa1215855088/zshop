package com.hnguigu.zshop.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hnguigu.zshop.common.util.RedisUtil;
import com.hnguigu.zshop.service.RedisCartService;
import com.hnguigu.zshop.vo.cart.BuyerCart;
import org.springframework.stereotype.Service;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-10 20:39
 **/
@Service
public class RedisCartServiceImpl implements RedisCartService {

    @Override
    public BuyerCart getCart(String userLoginName) {
        String cartInfo = RedisUtil.get(userLoginName+"cart");

        return JSONObject.parseObject(cartInfo, BuyerCart.class);

    }

    @Override
    public void addCart(String userLoginName, BuyerCart cart) {
        RedisUtil.set(userLoginName+"cart", JSON.toJSONString(cart));
    }
}
