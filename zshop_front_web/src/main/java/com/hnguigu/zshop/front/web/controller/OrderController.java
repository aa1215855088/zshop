package com.hnguigu.zshop.front.web.controller;

import com.hnguigu.zshop.common.exception.MyException;
import com.hnguigu.zshop.common.util.GeneratingOrderUtil;
import com.hnguigu.zshop.common.util.ResponseResult;
import com.hnguigu.zshop.domain.Customer;
import com.hnguigu.zshop.domain.Order;
import com.hnguigu.zshop.domain.OrderItem;
import com.hnguigu.zshop.service.OrderItemServer;
import com.hnguigu.zshop.service.OrderServer;
import com.hnguigu.zshop.service.RedisCartService;
import com.hnguigu.zshop.vo.cart.BuyerCart;
import com.hnguigu.zshop.vo.cart.BuyerItem;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-13 18:34
 **/
@Controller
@RequestMapping("/zshop")
public class OrderController {

    @Autowired
    private RedisCartService redisCartService;
    @Autowired
    private OrderServer orderServer;
    @Autowired
    private OrderItemServer orderItemServer;

    private BuyerCart buyerCart;

    @RequestMapping("myOrders")
    public String myOrders(Model model,HttpSession session) {
        //1 根据用户查询他拥有的订单号
        Customer user = (Customer) session.getAttribute("user");

        return "myOrders";
    }

    @RequestMapping("/order")
    public String order(HttpSession session, Model model, @RequestParam("id") Integer[] ids) {
        //获得用户
        Customer user = (Customer) session.getAttribute("user");
        //获得购物车
        BuyerCart cart = this.redisCartService.getCart(user.getLoginName());
        buyerCart = new BuyerCart();
        List<BuyerItem> items = cart.getItems();
        for (Iterator<BuyerItem> iterator = items.iterator(); iterator.hasNext(); ) {
            BuyerItem buyerItem = iterator.next();
            for (Integer id : ids) {
                if (buyerItem.getProduct().getId() == id) {
                    List<BuyerItem> items1 = buyerCart.getItems();
                    items1.add(buyerItem);
                    break;
                }
            }
        }
        model.addAttribute("cart", buyerCart);

        return "order";
    }

    @RequestMapping("generatingOrder")
    @ResponseBody
    public ResponseResult generatingOrder(HttpSession session) throws MyException {

        Customer user = (Customer) session.getAttribute("user");
        Order order = new Order();
        order.setId(GeneratingOrderUtil.generateOrder(user.getLoginName()));
        order.setCustomer(user);
        order.setNo("");
        order.setPrice(buyerCart.getTotalPrice());
        order.setCreateDate(new Date());
        //1 生成订单(订单自动生成所有没必要返回主键)
        int index = this.orderServer.save(order);
        if (index == 0) {
            return ResponseResult.fail("系统繁忙");
        }
        //2 生成订单明细
        List<BuyerItem> items = buyerCart.getItems();
        if (CollectionUtils.isEmpty(items)) {
            throw new MyException("");
        }
        for (BuyerItem item : items) {
            OrderItem orderItem = new OrderItem();
            orderItem.setNum(item.getAmount());
            orderItem.setOrder(order);
            orderItem.setProduct(item.getProduct());
            orderItem.setPrice(item.getPriceSum());
            int save = this.orderItemServer.save(orderItem);
        }
        //3 将生成订单的商品从购物车中删除
        BuyerCart cart = this.redisCartService.getCart(user.getLoginName());
        for (Iterator<BuyerItem> iterator = cart.getItems().iterator(); iterator.hasNext(); ) {
            BuyerItem next = iterator.next();
            for (BuyerItem item : items) {
                if (item.getProduct().getId() == next.getProduct().getId()) {
                    iterator.remove();
                    break;
                }
            }
        }

        this.redisCartService.addCart(user.getLoginName(), cart);

        return ResponseResult.success(order);
    }
}
