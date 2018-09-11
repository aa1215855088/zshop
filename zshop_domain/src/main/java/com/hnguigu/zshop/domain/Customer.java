package com.hnguigu.zshop.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-13 19:12
 **/
public class Customer implements Serializable {

    private static final long serialVersionUID = -599932646199567061L;

    /** 客户ID*/
    private int id;

    /** 客户名称*/
    private String name;

    /** 登录名称*/
    private String loginName;

    /** 登录密码*/
    private  String password;

    /** 客户号码*/
    private String phone;

    /** 客户地址*/
    private String address;

    /** 是否有效*/
    private Integer isValid;

    /** 注册时间*/
    private Date registDate;


    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }
}
