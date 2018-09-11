package com.hnguigu.zshop.domain;

import java.io.Serializable;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-08 16:05
 **/
public class ProductType implements Serializable {

    private static final long serialVersionUID = -5522469632252490798L;

    /** 商品类型ID*/
    private int typeId;

    /** 商品名称*/
    private String typeName;

    /** 商品状态*/
    private int typeStatus;


    public ProductType() {
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(int typeStatus) {
        this.typeStatus = typeStatus;
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", typeStatus=" + typeStatus +
                '}';
    }
}
