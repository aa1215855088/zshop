package com.hnguigu.zshop.dao;

import com.hnguigu.zshop.domain.Order;
import com.hnguigu.zshop.domain.OrderItem;

import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-15 09:05
 **/
public interface OrderItemDao {
    /**
     * 保存订单明细
     *
     * @param orderItem
     * @return
     */
    int save(OrderItem orderItem);

    /**
     * 根据订单查询订单明细
     *
     * @param order
     * @return
     */
    List<OrderItem> getOrderItemByOrderId(Order order);
}

