package com.hnguigu.zshop.vo.cart;

import com.hnguigu.zshop.domain.Product;

import java.io.Serializable;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-17 10:49
 **/
public class BuyerItem implements Serializable {

    private static final long serialVersionUID = 1L;

    //SKu对象
    private Product product;

    //是否有货
    private Boolean isHave = true;

    //购买的数量
    private Integer amount = 1;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Boolean getIsHave() {
        return isHave;
    }

    public void setIsHave(Boolean isHave) {
        this.isHave = isHave;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public double getPriceSum() {
        return getProduct().getPrice() * getAmount();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BuyerItem) {
            BuyerItem u = (BuyerItem) obj;
            return this.product.getName().equals(u.getProduct().getName());
        }
        return super.equals(obj);
    }
}
