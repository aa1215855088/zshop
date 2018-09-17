package com.hnguigu.zshop.service.impl;

import com.hnguigu.zshop.dao.OrderItemDao;
import com.hnguigu.zshop.domain.Order;
import com.hnguigu.zshop.domain.OrderItem;
import com.hnguigu.zshop.service.OrderItemServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-15 09:09
 **/
@Service
@Transactional
public class OrderItemServerImpl implements OrderItemServer {

    @Autowired
    private OrderItemDao orderItemDao;

    @Override
    public int save(OrderItem orderItem) {
        return this.orderItemDao.save(orderItem);
    }

    @Override
    public List<OrderItem> getOrderItemByOrderId(Order order) {
        return this.orderItemDao.getOrderItemByOrderId(order);
    }
}
