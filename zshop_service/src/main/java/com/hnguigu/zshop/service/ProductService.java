package com.hnguigu.zshop.service;

import com.hnguigu.zshop.common.exception.MyException;
import com.hnguigu.zshop.dao.Param.QueryProduct;
import com.hnguigu.zshop.domain.Product;
import com.hnguigu.zshop.dto.ProductDTO;

import java.io.OutputStream;
import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-10 18:01
 **/
public interface ProductService {

    /**
     * 查询所有商品
     *
     * @return
     */
    List<Product> findAll();

    /**
     * 添加商品
     *
     * @param productDTO
     */
    void add(ProductDTO productDTO) throws MyException;

    /**
     * 跟据商品名称查询商品
     *
     * @param name
     * @return
     */
    Boolean getProductByName(String name);

    /**
     * 根据商品ID查询商品
     * @param id
     * @return
     */
    Product getProductById(Integer id);

    /**
     * 根据图片路径返回图片
     * @param path
     * @param outputStream
     */
    void getImage(String path, OutputStream outputStream);

    /**
     * 修改商品
     * @param productDTO
     */
    void update(ProductDTO productDTO,Integer id) throws MyException;

    /**
     * 根据商品ID删除商品
     * @param id
     */
    void delProductById(Integer id);

    /**
     * 条件查询
     * @return
     */
    List<Product> queryProduct(QueryProduct queryProduct);

}
