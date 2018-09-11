package com.hnguigu.zshop.service;

import com.hnguigu.zshop.domain.Role;

import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-15 10:21
 **/
public interface RoleService {

    /**
     * 查询所有
     *
     * @return
     */
    List<Role> findAll();
}
