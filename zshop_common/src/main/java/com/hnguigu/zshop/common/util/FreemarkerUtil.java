package com.hnguigu.zshop.common.util;

import com.hnguigu.zshop.common.constant.FTPConstant;
import com.hnguigu.zshop.domain.Product;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.HashMap;
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
 * @create: 2018-10-22 09:04
 **/
public class FreemarkerUtil {

    public static String createProductIndo(Product product) throws IOException, TemplateException {
        Configuration conf = new Configuration(Configuration.VERSION_2_3_23);
        String dir = "E:\\javaweb\\项目实战\\在线商城系统\\zshop\\zshop_front_web\\src\\main\\test\\com\\hnguigu\\zshop\\front\\web\\controller\\";
        conf.setDirectoryForTemplateLoading(new File(dir));
        Template template = conf.getTemplate("product_info.ftl");
        Map<Object, Object> root = new HashMap();
        root.put("product", product);
        String path = dir + product.getId() + ".html";
        Writer out = new FileWriter(new File(path));
        template.process(root, out);
        FileInputStream fileInputStream = new FileInputStream(path);
        boolean result = FtpUtil.uploadFile(FTPConstant.ADD_RESS, FTPConstant.PORT, FTPConstant.USERNAME, FTPConstant.PASSWORD, FTPConstant.BASE_PATH, "", product.getId() + ".html", fileInputStream);
        out.flush();
        out.close();
        if (result) {
            return FTPConstant.URL + product.getId() + ".html";
        }
        return path;
    }
}
