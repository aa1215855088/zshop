package com.hnguigu.zshop.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-15 08:37
 **/
public class Order implements Serializable {
    private static final long serialVersionUID = -3905816892866012800L;

    /**
     * 订单ID
     */
    private String id;

    /**
     *
     */
    private String no;

    /**
     * 用户ID
     */
    private Customer customer;

    /**
     * 订单价格
     */
    private Float price;

    /**
     * 订单创建时间
     */
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
