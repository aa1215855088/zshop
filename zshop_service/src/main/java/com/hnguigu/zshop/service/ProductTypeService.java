package com.hnguigu.zshop.service;

import com.hnguigu.zshop.common.exception.MyException;
import com.hnguigu.zshop.domain.ProductType;

import java.util.List;
import java.util.Map;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-08 16:45
 **/
public interface ProductTypeService {

    /**
     * 查询所有
     *
     * @return
     */
    List<ProductType> listAll();

    /**
     * 判断商品类型名称是否已存在
     * 如果存在则抛出异常，
     * 否则保存
     *
     * @param typeName
     */
    void saveProductType(String typeName) throws MyException;

    /**
     * 修改商品类型状态
     *
     * @param map
     */
    void updateStatus(Map<String, Object> map) throws MyException;

    /**
     * 跟据商品类型ID查询商品
     *
     * @param id
     */
    ProductType getProductTypeById(Integer id);


    /**
     * 修改商品名称
     *
     * @param map
     */
    void updateName(Map<String, Object> map) throws MyException;

    /**
     * 根据ID删除商品类型
     * @param id
     */
    void delProductById(Integer id) throws MyException;

    /**
     * 查询所有启用的商品类型
     * @return
     */
    List<ProductType> findEnable();

}
