package com.hnguigu.zshop.service.impl;

import com.hnguigu.zshop.dao.SysuserDao;
import com.hnguigu.zshop.domain.Sysuser;
import com.hnguigu.zshop.service.SysuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-15 09:07
 **/
@Service
@Transactional
public class SysuserServiceImpl implements SysuserService {

    @Autowired
    private SysuserDao sysuserDao;

    @Override
    public List<Sysuser> findAll() {
        return this.sysuserDao.findAll();
    }

    @Override
    public Sysuser getSysuserById(int id) {
        return this.sysuserDao.getSysuserById(id);
    }

    @Override
    public List<Sysuser> find(Sysuser sysuser) {
        if (sysuser == null) {
            return this.findAll();
        }
        return this.sysuserDao.find(sysuser);
    }

    @Override
    public Sysuser getSysuserByName(String name) {
        return this.sysuserDao.getSysuserByName(name);
    }

    @Override
    public void updateIsValidById(Integer id, Integer isValid) {
        this.sysuserDao.updateIsValidById(id, isValid);
    }

    @Override
    public void updateSysuser(Sysuser sysuser) {
        this.sysuserDao.updateSysuser(sysuser);
    }

    @Override
    public void save(Sysuser sysuser) {

        this.sysuserDao.save(sysuser);
    }
}
