package com.hnguigu.zshop.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-10 17:55
 **/
public class Product implements Serializable {

    private static final long serialVersionUID = 5153716285360544288L;
    /**
     * 商品ID
     */
    private int id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private float price;

    /**
     * 商品信息
     */
    private String info;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 商品类型
     */
    private ProductType productType;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", info='" + info + '\'' +
                ", image='" + image + '\'' +
                ", productType=" + productType +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product) {
            Product u = (Product) obj;
            return this.getId() == u.getId();
        }
        return super.equals(obj);
    }
}
