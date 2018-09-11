package com.hnguigu.zshop.service.impl;

import com.hnguigu.zshop.dao.CustomerDao;
import com.hnguigu.zshop.domain.Customer;
import com.hnguigu.zshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-13 19:48
 **/
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<Customer> findAll() {
        return this.customerDao.findAll();
    }

    @Override
    public Customer getCustomerById(int id) {
        return this.customerDao.getCustomerById(id);
    }

    @Override
    public List<Customer> find(Customer customer) {
        return this.customerDao.find(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        this.customerDao.updateCustomer(customer);
    }

    @Override
    public void updateValid(Integer id, Integer isValid) {
        this.customerDao.updateValid(id, isValid);
    }

    @Override
    public boolean checkLoginName(String loginName) {

        Customer customer = this.customerDao.getCustomerByLoginName(loginName);
        if (customer != null) {
            return true;
        }

        return false;
    }

    @Override
    public Customer getCustomerByNanme(String username) {
        return this.customerDao.getCustomerByLoginName(username);
    }
}
