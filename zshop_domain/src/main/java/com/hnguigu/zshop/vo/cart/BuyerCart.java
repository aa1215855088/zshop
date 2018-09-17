package com.hnguigu.zshop.vo.cart;

import com.hnguigu.zshop.domain.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: zshop
 * @description: 购物车
 * @author: 徐子楼
 * @create: 2018-08-17 10:43
 **/
public class BuyerCart implements Serializable {

    private static final long serialVersionUID = -3089212529858429620L;

    /**
     * 商品结果集
     */
    private List<BuyerItem> items = new ArrayList<>();

    /**
     * 添加商品到购物车
     *
     * @param item
     */
    public void addProducts(BuyerItem item) {
        if (items.contains(item)) {
            for (BuyerItem buyerItem : items) {
                if (buyerItem.equals(item)) {
                    buyerItem.setAmount(buyerItem.getAmount() + item.getAmount());
                }
            }
        } else {
            items.add(item);
        }
    }

    public List<BuyerItem> getItems() {
        return items;
    }

    public void setItems(List<BuyerItem> items) {
        this.items = items;
    }

    //获取商品结算总价
    public float getTotalPrice() {
        float
                sum = 0;
        for (BuyerItem item : items) {
            sum += item.getPriceSum();
        }
        return sum;
    }
}
