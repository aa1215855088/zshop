package com.hnguigu.zshop.front.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnguigu.zshop.common.constant.PaginationConstant;
import com.hnguigu.zshop.common.util.ResponseResult;
import com.hnguigu.zshop.dao.Param.QueryProduct;
import com.hnguigu.zshop.domain.Product;
import com.hnguigu.zshop.domain.ProductType;
import com.hnguigu.zshop.service.ProductService;
import com.hnguigu.zshop.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.OutputStream;
import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-16 14:35
 **/
@Controller
@RequestMapping("/zshop")
public class MainController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;

    @ModelAttribute("findAllProductType")
    public List<ProductType> findAllProductType() {
        return this.productTypeService.findEnable();
    }


    @RequestMapping("/home")
    public String query(Integer pageNum, Model model, QueryProduct queryProduct) {
        ResponseResult responseResult = new ResponseResult();
        if (pageNum == null) {
            pageNum = PaginationConstant.PAGE_NUM;
        }
        PageHelper.startPage(pageNum, 8);

        List<Product> products = this.productService.queryProduct(queryProduct);
        if (products.size() == 0) {
            responseResult.setStatus(3);
            responseResult.setMessage("没有相关的数据,请重新查询!");
        }
        PageInfo<Product> pageInfo = new PageInfo<>(products);

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("queryProduct", queryProduct);
        model.addAttribute("responseResult", responseResult);
        return "main";
    }

    @RequestMapping("showImage")
    public void showImage(String path, OutputStream outputStream) {
        this.productService.getImage(path, outputStream);
    }


}

