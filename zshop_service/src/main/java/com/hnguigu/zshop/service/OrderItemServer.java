package com.hnguigu.zshop.service;

import com.hnguigu.zshop.domain.Order;
import com.hnguigu.zshop.domain.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-15 09:09
 **/

public interface OrderItemServer {
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
