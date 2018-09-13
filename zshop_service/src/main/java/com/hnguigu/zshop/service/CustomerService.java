package com.hnguigu.zshop.service;

import com.hnguigu.zshop.domain.Customer;

import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-13 19:47
 **/
public interface CustomerService {

    /**
     * 查询所有客户
     *
     * @return
     */
    List<Customer> findAll();

    /**
     * 跟据客户ID查询客户
     *
     * @param id
     * @return
     */
    Customer getCustomerById(int id);

    /**
     * 条件查询
     *
     * @return
     */
    List<Customer> find(Customer customer);

    /**
     * 修改客户
     *
     * @param customer
     */
    void updateCustomer(Customer customer);

    /**
     * 修改用户账号是否有效
     * 1:有效
     * 0:失效
     *
     * @param id
     * @param isValid
     */
    void updateValid(Integer id, Integer isValid);

    /**
     * 检验账号是否存在
     * true:存在
     * false:不存在
     *
     * @param loginName
     * @return
     */
    boolean checkLoginName(String loginName);

    /**
     * 跟据客户姓名查询客户
     *
     * @param username
     * @return
     */
    Customer getCustomerByNanme(String username);

    /**
     * 短信登录
     * 根据手机号查询用户
     *
     * @param phone
     * @return
     */
    Customer getCustomerByPhone(String phone);
}
