package com.hnguigu.zshop.bakend.message;

import com.alibaba.fastjson.JSON;
import com.hnguigu.zshop.common.util.FreemarkerUtil;
import com.hnguigu.zshop.domain.Product;
import com.hnguigu.zshop.service.ProductService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;

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
 * @program: zshop
 * @description:
 * @author: 徐子楼
 * @create: 2018-10-29 16:01
 **/
public class MyMessageListener implements MessageListener {
    @Autowired
    private ProductService productService;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;


        try {
            String productJsonStr = textMessage.getText();
            Product product = JSON.parseObject(productJsonStr, Product.class);
            String path = FreemarkerUtil.createProductIndo(product);
            this.productService.updateUrl(path, product.getId());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }
}
