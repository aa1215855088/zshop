package com.hnguigu.zshop.dao;

import com.hnguigu.zshop.dao.Param.QueryProduct;
import com.hnguigu.zshop.domain.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-10 17:55
 **/
public interface ProductDao {

    /**
     * 查询所有商品
     *
     * @return
     */
    List<Product> findAll();

    /**
     * 根据商品ID查询商品
     *
     * @param id
     * @return
     */
    Product getProductById(int id);

    /**
     * 添加商品
     *
     * @param product
     */
    void add(Product product);

    /**
     * 跟据商品名称查询商品
     *
     * @param name
     * @return
     */
    Product getProductByName(String name);

    /**
     * 根据商品类型Id查询商品
     *
     * @param id
     * @return
     */
    List<Product> getProductByTypeId(Integer id);

    /**
     * 跟据ID删除商品
     *
     * @param id
     */
    void delProductById(int id);

    /**
     * 修改
     *
     * @param
     */
    void update(Map<String, Object> map);

    /**
     * 查询
     *
     * @param queryProduct
     */
    List<Product> queryProduct(QueryProduct queryProduct);

    void updateUrl(@Param("path") String path, @Param("id") int id);
}
