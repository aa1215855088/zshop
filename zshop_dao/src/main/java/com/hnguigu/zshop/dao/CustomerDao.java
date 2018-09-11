package com.hnguigu.zshop.dao;

import com.hnguigu.zshop.domain.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-13 19:19
 **/
public interface CustomerDao {

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
     * 修改客户的权限
     *
     * @param id
     * @param isValid
     */
    void updateValid(@Param("id") Integer id, @Param("isValid") Integer isValid);

    /**
     * 跟据账号查询客户
     *
     * @param loginName
     * @return
     */
    Customer getCustomerByLoginName(String loginName);
}
