package com.hnguigu.zshop.front.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hnguigu.zshop.common.util.RedisUtil;
import com.hnguigu.zshop.domain.Customer;
import com.hnguigu.zshop.domain.Product;
import com.hnguigu.zshop.service.RedisCartService;
import com.hnguigu.zshop.vo.cart.BuyerCart;
import com.hnguigu.zshop.vo.cart.BuyerItem;
import com.hnguigu.zshop.front.web.utils.CookieCartUtils;
import com.hnguigu.zshop.service.ProductService;
import com.hnguigu.zshop.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-10 08:13
 **/
@Controller
@RequestMapping("/zshop")
public class CartController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private RedisCartService redisCartService;

    @RequestMapping("addCart")
    public String addCart(Integer id, HttpSession session,
                          HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        BuyerCart buyerCart = null;
        Customer user = (Customer) session.getAttribute("user");

        //1,获取Cookie中的购物车
        try {
            if (user != null) {

                buyerCart = redisCartService.getCart(user.getLoginName());
            } else {
                buyerCart = CookieCartUtils.getCart(request);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //2,Cookie中没有购物车, 创建购物车对象
        if (buyerCart == null) {
            buyerCart = new BuyerCart();
        }

        //3, 将当前款商品追加到购物车
        if (id != null) {
            BuyerItem buyerItem = new BuyerItem();
            Product productById = this.productService.getProductById(id);
            buyerItem.setProduct(productById);
            buyerItem.setAmount(1);
            buyerCart.addProducts(buyerItem);
        }

        //4,判断是否登录
        if (user != null) {
            // 登录了将购物车加入redis中做缓存.
            this.redisCartService.addCart(user.getLoginName(), buyerCart);
        } else {
            //未登录
            CookieCartUtils.addCart(response, buyerCart, 20 * 24 * 24);
        }
        //重定向
        return "redirect:cart";
    }

    @RequestMapping("cart")
    public String cart(HttpServletResponse response, HttpServletRequest request, Model model, HttpSession session) throws UnsupportedEncodingException {
        BuyerCart buyerCart = null;
        Customer user = (Customer) session.getAttribute("user");
        if (user != null) {
            buyerCart = redisCartService.getCart(user.getLoginName());
        } else {
            buyerCart = CookieCartUtils.getCart(request);
            if (buyerCart != null) {
                if (buyerCart.getItems().size() == 0) {
                    buyerCart = null;
                }
            }
        }
        model.addAttribute("buyerCart", buyerCart);
        return "cart";
    }

    @RequestMapping("clearCart")
    public String deleteCart(HttpServletResponse response, Integer id, HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
        Customer user = (Customer) session.getAttribute("user");
        if (user != null) {
            if (id != null) {
                BuyerCart buyerCart = redisCartService.getCart(user.getLoginName());
                List<BuyerItem> items = buyerCart.getItems();

                for (Iterator<BuyerItem> iterator = items.iterator(); iterator.hasNext(); ) {
                    BuyerItem buyerItem = iterator.next();
                    if (buyerItem.getProduct().getId() == id) {
                        iterator.remove();
                    }
                }
                this.redisCartService.addCart(user.getLoginName(), buyerCart);
            } else {
                RedisUtil.del(user.getLoginName()+"cart");
            }
        } else {
            if (id != null) {
                CookieCartUtils.clearCart(request, response, id);
            } else {
                //将添加的事件设置为0代表该cookie立即失效
                CookieCartUtils.addCart(response, new BuyerCart(), 0);
            }
        }
        return "redirect:cart";
    }

    @RequestMapping("clearCartByIds")
    public String deleteCartByIds(HttpServletResponse response, @RequestParam("id") Integer[] ids, HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = request.getSession();
        Customer user = (Customer) session.getAttribute("user");
        if (user != null) {
            BuyerCart buyerCart = redisCartService.getCart(user.getLoginName());
            List<BuyerItem> items = buyerCart.getItems();
            for (Iterator<BuyerItem> iterator = items.iterator(); iterator.hasNext(); ) {
                BuyerItem buyerItem = iterator.next();
                for (Integer id : ids) {
                    if (buyerItem.getProduct().getId() == id) {
                        iterator.remove();
                    }
                }
            }
            this.redisCartService.addCart(user.getLoginName(), buyerCart);
        } else {

            if (ids != null) {
                CookieCartUtils.clearCart(request, response, ids);
            }
        }
        return "redirect:cart";
    }
}
