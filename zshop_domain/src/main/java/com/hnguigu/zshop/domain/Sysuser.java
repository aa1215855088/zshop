package com.hnguigu.zshop.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-15 08:44
 **/
public class Sysuser implements Serializable {

    private static final long serialVersionUID = -3954577024943415265L;

    /** 管理员ID*/
    private Integer id;

    /** 管理员名*/
    private String name;

    /** 管理员账户名*/
    private String loginName;

    /** 登录密码*/
    private String password;

    /** 手机号码*/
    private String phone;

    /** 邮箱*/
    private String email;

    /** 是否有效*/
    private String isValid;

    /** 创建日期*/
    private Date createDate;

    /** 角色*/
    private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
