package com.hnguigu.zshop.bakend.web.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnguigu.zshop.bakend.vo.ProductVO;
import com.hnguigu.zshop.common.constant.PaginationConstant;
import com.hnguigu.zshop.common.exception.MyException;
import com.hnguigu.zshop.common.util.FreemarkerUtil;
import com.hnguigu.zshop.common.util.FtpUtil;
import com.hnguigu.zshop.common.util.IDUtils;
import com.hnguigu.zshop.common.util.ResponseResult;
import com.hnguigu.zshop.domain.Product;
import com.hnguigu.zshop.domain.ProductType;
import com.hnguigu.zshop.dto.ProductDTO;
import com.hnguigu.zshop.service.ProductService;
import com.hnguigu.zshop.service.ProductTypeService;
import org.apache.commons.beanutils.PropertyUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    /**
     * 地址
     */
    @Value("${IMAGE.FTP.ADDRESS}")
    private String host;
    /**
     * 端口号
     */
    @Value("${IMAGE.FTP.PORT}")
    private int port;
    /**
     * ftp用户名
     */
    @Value("${IMAGE.FTP.USERNAME}")
    private String userName;
    /**
     * ftp密码
     */
    @Value("${IMAGE.FTP.PASSWORD}")
    private String passWord;
    /**
     * 文件在服务器端保存的主目录
     */
    @Value("${IMAGE.FTP.BASEPATH}")
    private String basePath;
    /**
     * 访问图片时的基础url
     */
    @Value("${IMAGE.IMAGE.BASE.URL}")
    private String baseUrl;

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;

    @ModelAttribute("productTypes")
    public List<ProductType> loadProductType() throws MyException {

        List<ProductType> productTypes = null;
        try {
            productTypes = this.productTypeService.findEnable();
        } catch (Exception e) {
            throw new MyException("商品类型加载异常");
        }

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
    public String add(ProductVO productVO, Integer pageNum, HttpSession session) throws MyException {
        try {
            //1、给上传的图片生成新的文件名
            //1.1获取原始文件名
            String oldName = productVO.getImage().getOriginalFilename();
            //1.2使用IDUtils工具类生成新的文件名，新文件名 = newName + 文件后缀
            String newName = IDUtils.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            //1.3生成文件在服务器端存储的子目录
            String filePath = new DateTime().toString("/yyyy/MM/dd");

            //2、把前端输入信息，包括图片的url保存到数据库
            Product product = new Product();
            product.setName(productVO.getName());
            ProductType productType = this.productTypeService.getProductTypeById(productVO.getProductTypeId());
            product.setProductType(productType);
            product.setPrice(productVO.getPrice());
            product.setImage(baseUrl + filePath + "/" + newName);
            //3、把图片上传到图片服务器
            //3.1获取上传的io流
            InputStream input = productVO.getImage().getInputStream();
            //3.2调用FtpUtil工具类进行上传
            boolean result = FtpUtil.uploadFile(host, port, userName, passWord, basePath, filePath, newName, input);
            if (result) {
                this.productService.add(product);
                String path = FreemarkerUtil.createProductIndo(product);
                this.productService.updateUrl(path,product.getId());
                return "forward:findAll?pageNum=" + pageNum;
            } else {
                throw new MyException("文件上传失败");
            }
        } catch (Exception e) {
            throw new MyException("文件上传失败");
        }
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

    @RequestMapping(value = "updateProduct", method = {RequestMethod.POST, RequestMethod.GET}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String updateProduct(ProductVO productVO, Integer pageNum, HttpSession session, Model model) throws MyException {
        try {

            Map<String, Object> map = new HashMap<>();
            map.put("id", productVO.getId());
            ProductType productType = this.productTypeService.getProductTypeById(productVO.getProductTypeId());
            map.put("productTypeId", productType.getTypeId());
            map.put("name", productVO.getName());
            map.put("price", productVO.getPrice());
            if (productVO.getImage().getOriginalFilename() == "") {
                map.put("image", null);
                this.productService.update(map);
                return "forward:findAll?pageNum=" + pageNum;
            }
            //1、给上传的图片生成新的文件名
            //1.1获取原始文件名
            String oldName = productVO.getImage().getOriginalFilename();
            //1.2使用IDUtils工具类生成新的文件名，新文件名 = newName + 文件后缀
            String newName = IDUtils.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            //1.3生成文件在服务器端存储的子目录
            String filePath = new DateTime().toString("/yyyy/MM/dd");

            //2、把前端输入信息，包括图片的url保存到数据库


            map.put("image", baseUrl + filePath + "/" + newName);
            //3、把图片上传到图片服务器
            //3.1获取上传的io流
            InputStream input = productVO.getImage().getInputStream();
            //3.2调用FtpUtil工具类进行上传
            boolean result = FtpUtil.uploadFile(host, port, userName, passWord, basePath, filePath, newName, input);
            input.close();
            if (result) {
                this.productService.update(map);
                return "forward:findAll?pageNum=" + pageNum;
            } else {
                throw new MyException("文件上传失败");
            }
        } catch (Exception e) {
            throw new MyException("文件上传失败");
        }
    }

    @RequestMapping("delProduct")
    @ResponseBody
    public ResponseResult del(Integer id) {

        productService.delProductById(id);
        return ResponseResult.success("删除成功");
    }
}
