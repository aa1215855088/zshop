package com.hnguigu.zshop.bakend.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnguigu.zshop.bakend.vo.ProductVO;
import com.hnguigu.zshop.common.constant.PaginationConstant;
import com.hnguigu.zshop.common.util.ResponseResult;
import com.hnguigu.zshop.domain.Product;
import com.hnguigu.zshop.domain.ProductType;
import com.hnguigu.zshop.dto.ProductDTO;
import com.hnguigu.zshop.service.ProductService;
import com.hnguigu.zshop.service.ProductTypeService;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-10 17:51
 **/
@Controller
@RequestMapping("/backend/product")
public class ProductController {


    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;

    @ModelAttribute("productTypes")
    public List<ProductType> loadProductType() {
        List<ProductType> productTypes = productTypeService.findEnable();
        return productTypes;
    }

    @RequestMapping("findAll")
    public String findAll(Integer pageNum, Model model) {
        if (pageNum == null) {
            pageNum = PaginationConstant.PAGE_NUM;
        }
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);

        List<Product> productList = productService.findAll();

        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        model.addAttribute("pageInfo", pageInfo);
        return "productManager";
    }

    @RequestMapping("add")
    public String add(ProductVO productVO, Integer pageNum, HttpSession session) {
        String uploadPath = session.getServletContext().getRealPath("/uploda");
        ProductDTO productDTO = new ProductDTO();
        try {
            PropertyUtils.copyProperties(productDTO, productVO);
            productDTO.setFileName(productVO.getImage().getOriginalFilename());
            productDTO.setInputStream(productVO.getImage().getInputStream());
            productDTO.setUploadPath(uploadPath);
            productService.add(productDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "forward:findAll?pageNum=" + pageNum;
    }

    @RequestMapping("/checkName")
    @ResponseBody
    public Map<String, Object> checkName(String name) {
        Map<String, Object> map = new HashMap<>();
        //判断商品名称是否可用
        if (productService.getProductByName(name)) {
            map.put("valid", true);
        } else {
            map.put("valid", false);
            map.put("message", "商品（" + name + ")已存在");
        }
        return map;
    }

    @RequestMapping("showProduct")
    @ResponseBody
    public ResponseResult showProduct(Integer id, Model model) {

        Product product = productService.getProductById(id);
        return ResponseResult.success(product);
    }

    @RequestMapping("/showImage")
    public void showImage(String path, OutputStream outputStream) {
        productService.getImage(path, outputStream);
    }

    @RequestMapping("updateProduct")
    public String updateProduct(ProductVO productVO, Integer pageNum, HttpSession session, Model model) {
        ProductDTO productDTO = new ProductDTO();
        try {
            PropertyUtils.copyProperties(productDTO, productVO);
            if (productVO.getImage() != null) {
                String uploadPath = session.getServletContext().getRealPath("/uploda");
                productDTO.setFileName(productVO.getImage().getOriginalFilename());
                productDTO.setInputStream(productVO.getImage().getInputStream());
                productDTO.setUploadPath(uploadPath);
            }
            productService.update(productDTO, productVO.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "forward:findAll?pageNum=" + pageNum;
    }

    @RequestMapping("delProduct")
    @ResponseBody
    public ResponseResult del(Integer id) {

        productService.delProductById(id);
        return ResponseResult.success("删除成功");
    }
}
