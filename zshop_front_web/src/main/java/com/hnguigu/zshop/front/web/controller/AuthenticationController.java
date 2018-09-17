package com.hnguigu.zshop.front.web.controller;

import com.hnguigu.zshop.common.util.ResponseResult;
import com.hnguigu.zshop.domain.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-13 11:54
 **/
@Controller
@RequestMapping("/zshop")
public class AuthenticationController {

    @RequestMapping("/Authentication")
    @ResponseBody
    public ResponseResult MyAuthentication(HttpSession session) {
        Customer user = (Customer) session.getAttribute("user");
        if (user != null) {
            return ResponseResult.success();
        }
        return ResponseResult.fail("请登录!");
    }

}
