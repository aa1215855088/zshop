package com.hnguigu.zshop.dao;

import com.hnguigu.zshop.domain.Role;

import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-15 09:24
 **/
public interface RoleDao {

    /**
     * 查询所有
     *
     * @return
     */
    List<Role> findAll();
}
