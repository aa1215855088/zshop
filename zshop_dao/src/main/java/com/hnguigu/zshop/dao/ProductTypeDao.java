package com.hnguigu.zshop.dao;

import com.hnguigu.zshop.domain.ProductType;

import java.util.List;
import java.util.Map;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-08 16:09
 **/
public interface ProductTypeDao {

    /**
     * 查询所有商品类型
     *
     * @return
     */
    List<ProductType> listAll();

    /**
     * 添加商品类型
     *
     * @param typeName
     */
    void save(String typeName);

    /**
     * 根据名称查询
     *
     * @param typeName
     * @return
     */
    ProductType getProductTypeByName(String typeName);

    /**
     * 根据ID修改商品状态
     *
     * @param map
     */
    void updateStatus(Map<String, Object> map);

    /**
     * 根据商品类型Id查询商品
     *
     * @param id
     * @return
     */
    ProductType getProductTypeById(int id);

    /**
     * 修改商品名称
     *
     * @param map
     */
    void updateName(Map<String, Object> map);

    /**
     * 根据ID删除商品类型
     *
     * @param id
     */
    void delProductById(Integer id);

    /**
     * 查询所有可用商品类型
     * @return productTypes
     */
    List<ProductType> findEnable();

}
