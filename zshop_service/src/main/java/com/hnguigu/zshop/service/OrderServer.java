package com.hnguigu.zshop.service;

import com.hnguigu.zshop.domain.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * @param
     * @return
     */
    List<Order> getOrderByUserId(int userId);

    /**
     * 根据订单号查询订单
     * @param orderCode
     * @return
     */
    Order getOrderByOrderCode(String orderCode);
}
