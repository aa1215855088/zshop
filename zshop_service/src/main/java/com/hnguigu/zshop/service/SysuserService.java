package com.hnguigu.zshop.service;

import com.hnguigu.zshop.domain.Sysuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-15 09:07
 **/
public interface SysuserService {

    /**
     * 查询所有管理员
     *
     * @return
     */
    List<Sysuser> findAll();


    /**
     * 根据ID查询管理员
     *
     * @param id
     * @return
     */
    Sysuser getSysuserById(int id);


    /**
     * 条件查询
     *
     * @return
     */
    List<Sysuser> find(Sysuser sysuser);


    /**
     * 根据名称查询管理员
     *
     * @param name
     * @return
     */
    Sysuser getSysuserByName(String name);


    /**
     * 修改用户状态
     * 1:代表有效
     * 0:代表无效
     *
     * @param id
     * @param isValid
     */
    void updateIsValidById(@Param("id") Integer id, @Param("isValid") Integer isValid);

    /**
     * 修改管理员信息
     * @param sysuser
     */
    void updateSysuser(Sysuser sysuser);

    /**
     * 添加管理员
     * @param sysuser
     */
    void save(Sysuser sysuser);
}
