package com.hnguigu.zshop.service;

import com.hnguigu.zshop.vo.cart.BuyerCart;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-10 20:39
 **/
public interface RedisCartService {
    /**
     * 获得购物车
     *
     * @param userLoginName
     * @return
     */
    BuyerCart getCart(String userLoginName);

    /**
     * 添加商品至购物车
     *
     * @param userLoginName
     * @param cart
     */
    void addCart(String userLoginName, BuyerCart cart);
}
