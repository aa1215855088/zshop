package com.hnguigu.zshop.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-15 08:37
 **/
public class
Order implements Serializable {
    private static final long serialVersionUID = -3905816892866012800L;

    /**
     * 订单ID
     */
    private Integer id;

    /**
     * 订单号
     */
    private String orderCode;
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

    /**
     * 订单明细
     */
    private Set<OrderItem> orderItem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

    public Set<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(Set<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderCode='" + orderCode + '\'' +
                ", no='" + no + '\'' +
                ", customer=" + customer +
                ", price=" + price +
                ", createDate=" + createDate +
                ", orderItem=" + orderItem +
                '}';
    }
}
