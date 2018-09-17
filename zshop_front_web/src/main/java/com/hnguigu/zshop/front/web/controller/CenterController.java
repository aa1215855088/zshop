package com.hnguigu.zshop.front.web.controller;

import com.hnguigu.zshop.domain.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-10 21:33
 **/
@Controller
@RequestMapping("/zshop")
public class CenterController {

    @RequestMapping("/center")
    public String center() {
        return "center";
    }
}
