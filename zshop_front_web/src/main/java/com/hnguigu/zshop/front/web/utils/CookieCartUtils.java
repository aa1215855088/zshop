package com.hnguigu.zshop.front.web.utils;

import com.alibaba.fastjson.JSONObject;
import com.hnguigu.zshop.common.constant.CartConstant;
import com.hnguigu.zshop.vo.cart.BuyerCart;
import com.hnguigu.zshop.vo.cart.BuyerItem;
import org.apache.commons.collections.CollectionUtils;

import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-17 19:20
 **/
public class CookieCartUtils {



    /**
     * 获取购物车
     *
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    public static BuyerCart getCart(HttpServletRequest request) throws UnsupportedEncodingException {
        BuyerCart buyerCart = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (CartConstant.BUYER_CART.equals(cookie.getName())) {
                    /*  String cartStr = ZipUtils.unzip(cookie.getValue());*/
                    String cart = URLDecoder.decode(cookie.getValue().toString(), "utf-8");

                    buyerCart = JSONObject.parseObject(cart, BuyerCart.class);

                }
            }
        }
        return buyerCart;
    }

    /**
     * 添加购物车
     *
     * @param response
     * @param buyerCart
     * @throws UnsupportedEncodingException
     */
    public static void addCart(HttpServletResponse response, BuyerCart buyerCart, Integer time) throws UnsupportedEncodingException {
        //保存购物车到Cookie中
        //将对象转成Json格式


        Object cartJson = JSONObject.toJSON(buyerCart);
          /*  //将存入cookie的数据进行解压
            String zipCartJson = ZipUtils.zip(cartJson.toString());*/

        String cart = URLEncoder.encode(cartJson.toString(), "utf-8");

        Cookie cookie = new Cookie(CartConstant.BUYER_CART, cart);
        //设置path是可以共享cookie
        cookie.setPath("/");
        //设置Cookie过期时间: -1 表示关闭浏览器失效  0: 立即失效  >0: 单位是秒, 多少秒后失效
        cookie.setMaxAge(time);
        //5,Cookie写会浏览器
        response.addCookie(cookie);
    }

    /**
     * 根据商品删除
     *
     * @param request
     * @param id
     * @param response
     * @throws UnsupportedEncodingException
     */
    public static void clearCart(HttpServletRequest request, HttpServletResponse response, Integer id) throws UnsupportedEncodingException {
        BuyerCart buyerCart = getCart(request);

        List<BuyerItem> items = buyerCart.getItems();


        for (Iterator<BuyerItem> iterator = items.iterator(); iterator.hasNext(); ) {
            BuyerItem buyerItem = iterator.next();
            if (buyerItem.getProduct().getId() == id) {
                iterator.remove();
            }
        }

        //如果没有商品数据了 则删除Cookie
        if (CollectionUtils.isEmpty(items)) {
            addCart(response, buyerCart, 0);
        }

        addCart(response, buyerCart, 24 * 60 * 60);
    }

    /**
     * 删除多种商品
     *
     * @param request
     * @param response
     * @param ids
     * @throws UnsupportedEncodingException
     */
    public static void clearCart(HttpServletRequest request, HttpServletResponse response, Integer[] ids) throws UnsupportedEncodingException {
        BuyerCart buyerCart = getCart(request);


        if (ids == null && ids.length == 0) {
            addCart(response, buyerCart, 0);
        }

        List<BuyerItem> items = buyerCart.getItems();


        for (Iterator<BuyerItem> iterator = items.iterator(); iterator.hasNext(); ) {
            BuyerItem buyerItem = iterator.next();
            for (Integer id : ids) {
                if (buyerItem.getProduct().getId() == id) {
                    iterator.remove();
                }
            }
        }

        //如果没有商品数据了 则删除Cookie
        if (CollectionUtils.isEmpty(items)) {
            addCart(response, buyerCart, 0);
        }

        addCart(response, buyerCart, 24 * 60 * 60);
    }
}
