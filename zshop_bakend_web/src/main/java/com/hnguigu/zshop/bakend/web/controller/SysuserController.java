package com.hnguigu.zshop.bakend.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnguigu.zshop.common.constant.PaginationConstant;
import com.hnguigu.zshop.common.util.PasswordUtil;
import com.hnguigu.zshop.common.util.ResponseResult;
import com.hnguigu.zshop.domain.Role;
import com.hnguigu.zshop.domain.Sysuser;
import com.hnguigu.zshop.service.RoleService;
import com.hnguigu.zshop.service.SysuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @program: zshop
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-08-15 08:39
 **/
@Controller
@RequestMapping("/backend/sysuser")
public class SysuserController {

    @Autowired
    private SysuserService sysuserService;
    @Autowired
    private RoleService roleService;

    @ModelAttribute("roleList")
    public List<Role> roleList() {
        return roleService.findAll();
    }

    @RequestMapping("findAll")
    public String findAll(Integer pageNum, Model model) {
        if (pageNum == null) {
            pageNum = PaginationConstant.PAGE_NUM;
        }
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);

        List<Sysuser> sysusers = this.sysuserService.findAll();

        PageInfo<Sysuser> pageInfo = new PageInfo<>(sysusers);
        model.addAttribute("pageInfo", pageInfo);

        return "sysuserManager";
    }

    @RequestMapping("showSysuser")
    @ResponseBody
    public ResponseResult showSysuser(Integer id) {
        Sysuser sysuserById = this.sysuserService.getSysuserById(id);
        return ResponseResult.success(sysuserById);
    }

    @RequestMapping("updateSysuser")
    private void updateSysuser(Integer pageNum, Sysuser sysuser) {
        this.sysuserService.updateSysuser(sysuser);
    }

    @RequestMapping("checkLoginName")
    @ResponseBody
    public Map<String, Object> checkLoginName(String loginName) {
        Sysuser sysuserByName = this.sysuserService.getSysuserByName(loginName);
        Map<String, Object> map = new HashMap<>();
        if (sysuserByName != null) {
            map.put("valid", false);
            map.put("message", "改账户已存在");
            return map;
        } else {
            map.put("valid", true);
            return map;
        }
    }

    @RequestMapping("add")
    public String save(Integer pageNum, Sysuser sysuser) {
        String password = PasswordUtil.md5(sysuser.getPassword(), sysuser.getLoginName());
        sysuser.setPassword(password);
        this.sysuserService.save(sysuser);
        return "forward:findAll?pageNum=" + pageNum;
    }

    /**
     * 条件查询
     */
    @RequestMapping("find")
    public String find(Integer pageNum, Sysuser sysuser, Model model) {

        if (pageNum == null) {
            pageNum = PaginationConstant.PAGE_NUM;
        }
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        List<Sysuser> sysuserList = this.sysuserService.find(sysuser);

        PageInfo<Sysuser> pageInfo = new PageInfo<>(sysuserList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("findSysuser", sysuser);
        return "sysuserManager";
    }

    @RequestMapping("updateValid")
    @ResponseBody
    public ResponseResult updateValid(Integer id, Integer isValid) {
        this.sysuserService.updateIsValidById(id, isValid);
        return ResponseResult.success("修改成功");
    }
}
