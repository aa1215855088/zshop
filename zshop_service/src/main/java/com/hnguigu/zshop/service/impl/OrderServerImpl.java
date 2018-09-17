package com.hnguigu.zshop.service.impl;

import com.hnguigu.zshop.dao.OrderDao;
import com.hnguigu.zshop.domain.Order;
import com.hnguigu.zshop.service.OrderServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-15 08:45
 **/
@Service
@Transactional
public class OrderServerImpl implements OrderServer {

    @Autowired
    private OrderDao orderDao;

    @Override
    public int save(Order order) {

        return this.orderDao.save(order);
    }

    @Override
    public List<Order> getOrderByUserId(String loginName) {
        return this.orderDao.getOrderByUserId(loginName);
    }
}
