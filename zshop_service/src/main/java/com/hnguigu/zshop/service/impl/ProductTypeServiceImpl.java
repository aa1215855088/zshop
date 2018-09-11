package com.hnguigu.zshop.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hnguigu.zshop.common.exception.MyException;
import com.hnguigu.zshop.common.util.RedisUtil;
import com.hnguigu.zshop.dao.ProductDao;
import com.hnguigu.zshop.domain.Product;
import com.hnguigu.zshop.domain.ProductType;
import com.hnguigu.zshop.dao.ProductTypeDao;
import com.hnguigu.zshop.service.ProductTypeService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-08 16:46
 **/
@Service("productTypeService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeDao productTypeDao;
    @Autowired
    private ProductDao productDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<ProductType> listAll() {
        return this.productTypeDao.listAll();
    }

    @Override
    public void saveProductType(String typeName) throws MyException {
        //根据名称查询
        ProductType productType = this.productTypeDao.getProductTypeByName(typeName);

        //判断类型是否存在
        if (productType != null) {
            throw new MyException("该商品类型已存在");
        }

        this.productTypeDao.save(typeName);
    }

    @Override
    public void updateStatus(Map<String, Object> map) throws MyException {

        List<Product> productList = productDao.getProductByTypeId(Integer.valueOf((String) map.get("id")));
        if (CollectionUtils.isNotEmpty(productList)) {
            throw new MyException("该类型下还有商品，不能修改状态");
        }
        this.productTypeDao.updateStatus(map);
    }

    @Override
    public ProductType getProductTypeById(Integer id) {
        return this.productTypeDao.getProductTypeById(id);
    }

    @Override
    public void updateName(Map<String, Object> map) throws MyException {

        ProductType productType = this.productTypeDao.getProductTypeByName((String) map.get("name"));
        if (productType != null) {
            throw new MyException("该商品类型已存在");
        }
        this.productTypeDao.updateName(map);
    }

    @Override
    public void delProductById(Integer id) throws MyException {
        List<Product> productList = productDao.getProductByTypeId(id);
        if (CollectionUtils.isNotEmpty(productList)) {
            throw new MyException("该类型下还有商品，不能删除");
        }
        this.productTypeDao.delProductById(id);
    }

    @Override
    public List<ProductType> findEnable() {

        if (RedisUtil.existsObject("productTypeList")) {
            String productTypeList1 = RedisUtil.get("productTypeList");
            ArrayList<ProductType> productTypes = JSON.parseObject(productTypeList1, new TypeReference<ArrayList<ProductType>>() {
            });
            return productTypes;
        }

        List<ProductType> productTypeList = this.productTypeDao.findEnable();

        RedisUtil.set("productTypeList", JSON.toJSONString(productTypeList));
        return productTypeList;
    }
}
