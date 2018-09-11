package com.hnguigu.zshop.bakend.vo;

import com.hnguigu.zshop.domain.Customer;

import java.util.Date;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-13 22:44
 **/
public class QueryCustomerVO extends Customer {

    private String name;

    private String loginName;

    private String phone;

    private String address;

    private Integer isValid;

    @Override
    public String toString() {
        return "QueryCustomerVO{" +
                "name='" + name + '\'' +
                ", loginName='" + loginName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", isValid=" + isValid +
                '}';
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getLoginName() {
        return super.getLoginName();
    }

    @Override
    public void setLoginName(String loginName) {
        super.setLoginName(loginName);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public String getPhone() {
        return super.getPhone();
    }

    @Override
    public void setPhone(String phone) {
        super.setPhone(phone);
    }

    @Override
    public String getAddress() {
        return super.getAddress();
    }

    @Override
    public void setAddress(String address) {
        super.setAddress(address);
    }

    @Override
    public int getIsValid() {
        return super.getIsValid();
    }

    @Override
    public void setIsValid(int isValid) {
        super.setIsValid(isValid);
    }

    @Override
    public Date getRegistDate() {
        return super.getRegistDate();
    }

    @Override
    public void setRegistDate(Date registDate) {
        super.setRegistDate(registDate);
    }
}

