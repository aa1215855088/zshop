package com.hnguigu.zshop.front.web.controller;

import com.hnguigu.zshop.common.exception.MyException;
import com.hnguigu.zshop.common.util.ResponseResult;
import com.hnguigu.zshop.domain.Customer;
import com.hnguigu.zshop.service.CustomerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-13 14:31
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/addUser")
    public String addUser(Customer customer, HttpSession session) throws MyException {
        int index = this.customerService.addUser(customer);
        if (index == 0) {
            throw new MyException("添加用户失败");
        }
        Customer user = this.customerService.getCustomerById(customer.getId());
        session.setAttribute("user", user);
        return "redirect:/zshop/home";
    }

    @RequestMapping("checkLoginName")
    @ResponseBody
    public Map<String, Object> checkLoginName(String loginName) {
        Map<String, Object> map = new HashMap<>();

        if (customerService.checkLoginName(loginName)) {
            map.put("valid", false);
            map.put("message", "该账号已存在");
        } else {
            map.put("valid", true);
        }
        return map;
    }

    @RequestMapping("checkPhone")
    @ResponseBody
    public Map<String, Object> checkPhone(String phone) {
        Map<String, Object> map = new HashMap<>();
        Customer customer = this.customerService.getCustomerByPhone(phone);
        if (customer != null) {
            map.put("valid", false);
            map.put("message", "该手机号已经被注册过!");
        } else {
            map.put("valid", true);
        }
        return map;
    }

    @RequestMapping("checkPassword")
    @ResponseBody
    public Map<String, Object> checkPassword(String oldPassword, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        Customer user = (Customer) session.getAttribute("user");
        if (!user.getPassword().equals(oldPassword)) {
            map.put("valid", false);
            map.put("message", "原密码有误!");
        } else {
            map.put("valid", true);
        }
        return map;
    }

    @RequestMapping("updatePassword")
    @ResponseBody
    public ResponseResult updatePassword(HttpSession session, String password) {
        Customer user = (Customer) session.getAttribute("user");
        user.setPassword(password);
        int index = this.customerService.updateCustomer(user);
        if (index == 0) {
            ResponseResult.fail("系统繁忙！请重试。");
        }
        Subject subject = SecurityUtils.getSubject();
        //退出登录
        subject.logout();
        return ResponseResult.success("密码修改成功！请重新登录。");
    }
}
