package com.hnguigu.zshop.front.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
