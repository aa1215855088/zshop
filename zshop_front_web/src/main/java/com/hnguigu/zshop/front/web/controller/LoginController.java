package com.hnguigu.zshop.front.web.controller;

import com.alibaba.fastjson.JSON;
import com.hnguigu.zshop.common.util.RedisUtil;
import com.hnguigu.zshop.common.util.ResponseResult;
import com.hnguigu.zshop.service.RedisCartService;
import com.hnguigu.zshop.vo.cart.BuyerCart;
import com.hnguigu.zshop.front.web.utils.CookieCartUtils;
import com.hnguigu.zshop.vo.cart.BuyerItem;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-10 07:54
 **/
@Controller
@RequestMapping("/zshop")
public class LoginController {

    @Autowired
    private RedisCartService redisCartService;

    @RequestMapping("/login")
    @ResponseBody
    public ResponseResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return ResponseResult.fail("没有此用户!");
        } catch (IncorrectCredentialsException ice) {
            return ResponseResult.fail("密码不正确!");
        } catch (LockedAccountException lae) {
            return ResponseResult.fail("账户已锁定!");
        } catch (ExcessiveAttemptsException eae) {
            return ResponseResult.fail("用户名或密码错误次数过多!");
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            ae.printStackTrace();
            return ResponseResult.fail("用户名或密码有误,请重新输入!");
        }

        if (subject.isAuthenticated()) {
            //将存在cookie中的购物车信息删除 缓存到redis
            BuyerCart cookieCart = CookieCartUtils.getCart(request);
            if (cookieCart != null) {
                BuyerCart redisCart = this.redisCartService.getCart(username + "cart");
                if (redisCart != null) {
                    List<BuyerItem> items = cookieCart.getItems();
                    for (BuyerItem cookieItem : items) {
                        redisCart.addProducts(cookieItem);
                    }
                    this.redisCartService.addCart(username, redisCart);
                } else {
                    this.redisCartService.addCart(username, cookieCart);
                }


                CookieCartUtils.addCart(response, new BuyerCart(), 0);
            }
            return ResponseResult.success();
        } else {
            token.clear();
            return ResponseResult.fail("系统正在维护请稍后重试!");
        }
    }

    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/zshop/home";
    }
}
