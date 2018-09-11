package com.hnguigu.zshop.dao.Param;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-16 20:50
 **/
public class QueryProduct {

    private String productName;

    private Float maxPrice;

    private Float minPrice;

    private Integer productTypeId;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Float minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }
}
