/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.util.HashSet;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Message;
import com.sammyun.Pageable;
import com.sammyun.entity.Admin;
import com.sammyun.entity.BaseEntity.Save;
import com.sammyun.entity.Role;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.AdminService;
import com.sammyun.service.RoleService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.util.ImUserUtil;

/**
 * Controller - 管理员
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Controller("adminAdminController")
@RequestMapping("/console/admin")
public class AdminController extends BaseController
{

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Resource(name = "roleServiceImpl")
    private RoleService roleService;

    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;
    

    /**
     * 检查用户名是否存在
     */
    @RequestMapping(value = "/check_username", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkUsername(String username)
    {
        if (StringUtils.isEmpty(username))
        {
            return false;
        }
        if (adminService.usernameExists(username))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("dictSchools", dictSchoolService.findAll());
        model.addAttribute("menuId", Admin.class.getSimpleName());
        return "/console/admin/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Admin admin,Long dictSchoolId, Long[] roleIds, RedirectAttributes redirectAttributes, ModelMap model)
    {   
        DictSchool dictSchool = dictSchoolService.find(dictSchoolId);
        admin.setDictSchool(dictSchool);
        admin.setRoles(new HashSet<Role>(roleService.findList(roleIds)));
        if (!isValid(admin, Save.class))
        {
            return ERROR_VIEW;
        }
        if (adminService.usernameExists(admin.getUsername()))
        {
            return ERROR_VIEW;
        }
        admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
        admin.setIsLocked(false);
        admin.setLoginFailureCount(0);
        admin.setLockedDate(null);
        admin.setLoginDate(null);
        admin.setLoginIp(null);
        adminService.save(admin);
        model.addAttribute("menuId", Admin.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        
        //生成默认班次
        ImUserUtil imUserUtil = new ImUserUtil();
        imUserUtil.getDefalutWorkSetting(dictSchool);
        
        //生成默认发件人
        imUserUtil.getSystemMember(dictSchool);
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("admin", adminService.find(id));
        model.addAttribute("dictSchools", dictSchoolService.findAll());
        model.addAttribute("menuId", Admin.class.getSimpleName());
        return "/console/admin/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Admin admin, Long dictSchoolId,Long[] roleIds, RedirectAttributes redirectAttributes, ModelMap model)
    {
        DictSchool dictSchool =dictSchoolService.find(dictSchoolId);
        admin.setDictSchool(dictSchool);
        admin.setRoles(new HashSet<Role>(roleService.findList(roleIds)));
        if (!isValid(admin))
        {
            return ERROR_VIEW;
        }
        Admin pAdmin = adminService.find(admin.getId());
        if (pAdmin == null)
        {
            return ERROR_VIEW;
        }
        if (StringUtils.isNotEmpty(admin.getPassword()))
        {
            admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
        }
        else
        {
            admin.setPassword(pAdmin.getPassword());
        }
        if (pAdmin.getIsLocked() && !admin.getIsLocked())
        {
            admin.setLoginFailureCount(0);
            admin.setLockedDate(null);
        }
        else
        {
            admin.setIsLocked(pAdmin.getIsLocked());
            admin.setLoginFailureCount(pAdmin.getLoginFailureCount());
            admin.setLockedDate(pAdmin.getLockedDate());
        }
        adminService.update(admin, "username", "loginDate", "loginIp", "orders");
        model.addAttribute("menuId", Admin.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", adminService.findPage(pageable));
        model.addAttribute("menuId", Admin.class.getSimpleName());
        return "/console/admin/list";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        if (ids.length >= adminService.count())
        {
            return Message.error("console.common.deleteAllNotAllowed");
        }
        adminService.delete(ids);
        return SUCCESS_MESSAGE;
    }

}
