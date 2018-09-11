package com.hnguigu.zshop.domain;

import java.io.Serializable;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-15 08:47
 **/
public class Role implements Serializable {


    private static final long serialVersionUID = 8257911335259940977L;

    private int id;

    private String name;


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
}
