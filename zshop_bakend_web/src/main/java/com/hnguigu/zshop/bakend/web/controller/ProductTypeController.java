package com.hnguigu.zshop.bakend.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnguigu.zshop.common.constant.ResponseStatusConstant;
import com.hnguigu.zshop.common.exception.MyException;
import com.hnguigu.zshop.common.constant.PaginationConstant;
import com.hnguigu.zshop.common.util.ResponseResult;
import com.hnguigu.zshop.domain.ProductType;
import com.hnguigu.zshop.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author lenovo
 */
@Controller
@RequestMapping("/backend/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping("/findAll")
    public String findAll(Integer pageNum, Model model) {
        if (pageNum == null) {
            pageNum = PaginationConstant.PAGE_NUM;
        }
        //设置分页
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);

        //查询所有数据
        List<ProductType> productTypeList = productTypeService.listAll();

        //将查询出来的结果放到PageInfo对象里面
        PageInfo<ProductType> pageInfo = new PageInfo<>(productTypeList);

        model.addAttribute("pageInfo", pageInfo);
        return "productTypeManager";
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(String typeName) {

        try {
            productTypeService.saveProductType(typeName);
            //添加商品类型
            return ResponseResult.success("添加成功");
        } catch (MyException e) {
            //e.printStackTrace();
            return ResponseResult.fail(e.getMessage());
        }
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public ResponseResult updateStatus(@RequestParam Map<String, Object> map) {

        //根据传过来的状态和ID修改
        try {
            productTypeService.updateStatus(map);
            return ResponseResult.success("修改成功");
        } catch (MyException e) {
//            e.printStackTrace();
            return ResponseResult.fail(e.getMessage());
        }
    }

    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(Integer id) {

        ProductType productType = productTypeService.getProductTypeById(id);

        return ResponseResult.success(productType);
    }

    @ResponseBody
    @RequestMapping("/updateName")
    public ResponseResult updateName(@RequestParam Map<String, Object> map) {

        try {
            productTypeService.updateName(map);
            return ResponseResult.success("修改成功");
        } catch (MyException e) {
            //e.printStackTrace();
            return ResponseResult.fail(e.getMessage());
        }
    }

    @RequestMapping("/del")
    @ResponseBody
    public ResponseResult del(Integer id) {

        try {
            productTypeService.delProductById(id);
            return ResponseResult.success("删除成功");
        } catch (MyException e) {
//            e.printStackTrace();
            return ResponseResult.fail(e.getMessage());
        }

    }
}
