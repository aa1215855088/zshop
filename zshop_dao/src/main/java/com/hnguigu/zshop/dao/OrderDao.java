package com.hnguigu.zshop.dao;

import com.hnguigu.zshop.domain.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-15 08:44
 **/
public interface OrderDao {
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
     * @param userId
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
