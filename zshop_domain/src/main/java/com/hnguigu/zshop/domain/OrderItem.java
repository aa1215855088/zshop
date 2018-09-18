package com.hnguigu.zshop.domain;

import java.io.Serializable;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-15 09:02
 **/
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 3956563823973567709L;

    /**
     * 订单明细ID
     */
    private int id;

    /**
     * 商品
     */
    private Product product;

    /**
     * 商品数量
     */
    private int num;

    /**
     * 商品价格(商品价格*sum)
     */
    private float price;

    /**
     * 商品订单
     */
    private Order order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", product=" + product +
                ", num=" + num +
                ", price=" + price +
                ", order=" + order +
                '}';
    }
}
