package com.hnguigu.zshop.bakend.vo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-11 20:44
 **/
public class ProductVO {

    private Integer id;

    private String name;

    private Double price;

    private CommonsMultipartFile image;

    private Integer productTypeId;

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CommonsMultipartFile getImage() {
        return image;
    }

    public void setImage(CommonsMultipartFile image) {
        this.image = image;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }
}
