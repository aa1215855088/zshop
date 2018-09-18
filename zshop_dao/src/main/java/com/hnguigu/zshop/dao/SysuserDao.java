package com.hnguigu.zshop.dao;

import com.hnguigu.zshop.domain.Sysuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-15 08:48
 **/
public interface SysuserDao {
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
     * 跟据账户查询管理员
     *
     * @param loginName
     * @return
     */
    Sysuser getSysuserByName(String loginName);


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
     *
     * @param sysuser
     */
    void updateSysuser(Sysuser sysuser);

    /**
     * 保存管理员
     *
     * @param sysuser
     */
    void save(Sysuser sysuser);
}
