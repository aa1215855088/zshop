package com.hnguigu.zshop.service.impl;

import com.hnguigu.zshop.common.exception.MyException;
import com.hnguigu.zshop.common.util.StringUtils;
import com.hnguigu.zshop.dao.Param.QueryProduct;
import com.hnguigu.zshop.dao.ProductDao;
import com.hnguigu.zshop.domain.Product;
import com.hnguigu.zshop.domain.ProductType;
import com.hnguigu.zshop.dto.ProductDTO;
import com.hnguigu.zshop.service.ProductService;
import com.hnguigu.zshop.service.ProductTypeService;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-10 18:01
 **/
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll() {
        return this.productDao.findAll();
    }

    @Override
    public void add(ProductDTO productDTO) throws MyException {
        //1.文件上传

        String fileName = StringUtils.getFileName(productDTO.getFileName());

        /*  String filePath = productDTO.getUploadPath() + "/" + fileName;*/

        String filePath = "E:/javaweb/项目实战/在线商城系统/zshop/zshop_bakend_web/src/main/webapp/uploda/" + fileName;
        try {
            StreamUtils.copy(productDTO.getInputStream(), new FileOutputStream(filePath));
        } catch (IOException e) {
            throw new MyException("文件上传失败" + e.getMessage());
        }

        //2.保存到数据库
        Product product = new Product();

        try {
            PropertyUtils.copyProperties(product, productDTO);
            ProductType productType = new ProductType();
            productType.setTypeId(productDTO.getProductTypeId());
            product.setProductType(productType);
            product.setImage(filePath);
            productDao.add(product);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Boolean getProductByName(String name) {
        Product productByName = productDao.getProductByName(name);
        if (productByName != null) {
            return false;
        }
        return true;
    }

    @Override
    public Product getProductById(Integer id) {

        return productDao.getProductById(id);
    }

    @Override
    public void getImage(String path, OutputStream outputStream) {
        try {
            StreamUtils.copy(new FileInputStream(path), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ProductDTO productDTO, Integer id) throws MyException {
        String filePath = "";
        if (org.apache.commons.lang3.StringUtils.isNotBlank(productDTO.getFileName())) {
            String fileName = StringUtils.getFileName(productDTO.getFileName());
            /*  String filePath = productDTO.getUploadPath() + "/" + fileName;*/
            filePath = "E:/javaweb/项目实战/在线商城系统/zshop/zshop_bakend_web/src/main/webapp/uploda/" + fileName;
            try {
                StreamUtils.copy(productDTO.getInputStream(), new FileOutputStream(filePath));
            } catch (IOException e) {
                throw new MyException("文件上传失败" + e.getMessage());
            }

        }
        Product product = new Product();
        try {
            PropertyUtils.copyProperties(product, productDTO);
            ProductType productType = new ProductType();
            productType.setTypeId(productDTO.getProductTypeId());
            product.setProductType(productType);
            product.setImage(filePath);
            productDao.update(product, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delProductById(Integer id) {
        productDao.delProductById(id);
    }

    @Override
    public List<Product> queryProduct(QueryProduct queryProduct) {
        if (queryProduct == null) {
            return this.productDao.findAll();
        }
        return this.productDao.queryProduct(queryProduct);
    }
}
