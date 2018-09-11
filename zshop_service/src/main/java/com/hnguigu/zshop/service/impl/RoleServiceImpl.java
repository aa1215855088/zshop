package com.hnguigu.zshop.service.impl;

import com.hnguigu.zshop.dao.RoleDao;
import com.hnguigu.zshop.domain.Role;
import com.hnguigu.zshop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-15 10:22
 **/
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return this.roleDao.findAll();
    }
}
