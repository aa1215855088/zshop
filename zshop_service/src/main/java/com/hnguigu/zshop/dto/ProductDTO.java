package com.hnguigu.zshop.dto;

import java.io.InputStream;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-11 20:56
 **/
public class ProductDTO {

    private String name;

    private Double price;

    private Integer productTypeId;

    /** 文件输入流*/
    private InputStream inputStream;

    /** 文件名称*/
    private String fileName;

    /** 文件路径*/
    private String uploadPath;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
