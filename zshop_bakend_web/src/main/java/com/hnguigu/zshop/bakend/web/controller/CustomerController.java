package com.hnguigu.zshop.bakend.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnguigu.zshop.bakend.vo.QueryCustomerVO;
import com.hnguigu.zshop.common.constant.PaginationConstant;
import com.hnguigu.zshop.common.util.ResponseResult;
import com.hnguigu.zshop.domain.Customer;
import com.hnguigu.zshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.naming.ldap.PagedResultsControl;
import javax.naming.ldap.PagedResultsResponseControl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-13 19:02
 **/
@Controller
@RequestMapping("/backend/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("findAll")
    public String findAll(Integer pageNum, Model model) {
        List<Customer> customerList = null;
        if (pageNum == null) {
            pageNum = PaginationConstant.PAGE_NUM;
        }
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);

        customerList = this.customerService.findAll();

        PageInfo<Customer> pageInfo = new PageInfo<>(customerList);
        model.addAttribute("pageInfo", pageInfo);
        return "customerManager";
    }

    @RequestMapping("updateValid")
    @ResponseBody
    public ResponseResult updateValid(Integer id, Integer isValid) {
        this.customerService.updateValid(id, isValid);
        return ResponseResult.success("修改成功");
    }

    @RequestMapping("find")
    public String find(Integer bbq, Integer pageNum, Customer customer, Model model) {
        if (pageNum == null) {
            pageNum = PaginationConstant.PAGE_NUM;
        }
        if (bbq != null) {
            pageNum = PaginationConstant.PAGE_NUM;
        }
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);

        List<Customer> customerList = customerService.find(customer);

        PageInfo<Customer> pageInfo = new PageInfo<>(customerList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("queryCustomer", customer);
        return "customerManager";
    }


    @RequestMapping("showCustomer")
    @ResponseBody
    public ResponseResult showCustomer(Integer id) {
        Customer customerById = this.customerService.getCustomerById(id);
        return ResponseResult.success(customerById);
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

    @RequestMapping("updateCustomer")
    public String updateCustomer(Customer customer, Integer pageNum) {

        this.customerService.
                updateCustomer(customer);
        return "forward:findAll?pageNum=" + pageNum;
    }
}
