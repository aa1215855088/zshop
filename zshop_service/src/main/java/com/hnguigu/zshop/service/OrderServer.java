package com.hnguigu.zshop.service;

import com.hnguigu.zshop.domain.Order;

import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-15 08:44
 **/
public interface OrderServer {
    /**
     * 添加订单
     *
     * @param order
     * @return
     */
    int save(Order order);

    /**
     * 查询用户的订单
     *
     * @param loginName
     * @return
     */
    List<Order> getOrderByUserId(String loginName);
}
