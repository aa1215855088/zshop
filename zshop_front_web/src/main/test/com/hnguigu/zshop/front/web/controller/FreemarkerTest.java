package com.hnguigu.zshop.front.web.controller;

import com.hnguigu.zshop.domain.Product;
import com.hnguigu.zshop.service.ProductService;
import com.hnguigu.zshop.service.impl.ProductServiceImpl;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @program: 在线商城系统
 * @description:
 * @author: 徐子楼
 * @create: 2018-10-21 16:30
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/spring-*.xml")
public class FreemarkerTest {

    @Autowired
    private ProductService productService;

    @Test
    public void test1() throws IOException, TemplateException {
        Configuration conf = new Configuration(Configuration.VERSION_2_3_23);
        String dir = "E:\\javaweb\\项目实战\\在线商城系统\\zshop\\zshop_front_web\\src\\main\\test\\com\\hnguigu\\zshop\\front\\web\\controller\\";
        conf.setDirectoryForTemplateLoading(new File(dir));
        Template template = conf.getTemplate("product_info.ftl");
        Map<Object, Object> root = new HashMap();
        List<Product> products = this.productService.findAll();
        for (Product product : products) {
            root.put("product", product);
            Writer out = new FileWriter(new File(dir + product.getId() + ".html"));
            template.process(root, out);
            out.flush();
            out.close();
        }


    }

    public static void main(String[] args) throws IOException, TemplateException {

    }
}
