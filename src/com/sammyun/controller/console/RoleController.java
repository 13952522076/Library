/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Message;
import com.sammyun.Pageable;
import com.sammyun.entity.Role;
import com.sammyun.service.RoleService;

/**
 * Controller - 角色
 * 


 */
@Controller("adminRoleController")
@RequestMapping("/console/role")
public class RoleController extends BaseController
{

    @Resource(name = "roleServiceImpl")
    private RoleService roleService;

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        model.addAttribute("menuId", Role.class.getSimpleName());
        return "/console/role/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Role role, RedirectAttributes redirectAttributes, ModelMap model)
    {
        if (!isValid(role))
        {
            return ERROR_VIEW;
        }
        role.setIsSystem(false);
        role.setAdmins(null);
        roleService.save(role);
        model.addAttribute("menuId", Role.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("role", roleService.find(id));
        model.addAttribute("menuId", Role.class.getSimpleName());
        return "/console/role/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Role role, RedirectAttributes redirectAttributes, ModelMap model)
    {
        if (!isValid(role))
        {
            return ERROR_VIEW;
        }
        Role pRole = roleService.find(role.getId());
        if (pRole == null || pRole.getIsSystem())
        {
            return ERROR_VIEW;
        }
        roleService.update(role, "isSystem", "admins");
        model.addAttribute("menuId", Role.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", roleService.findPage(pageable));
        model.addAttribute("menuId", Role.class.getSimpleName());
        return "/console/role/list";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        if (ids != null)
        {
            for (Long id : ids)
            {
                Role role = roleService.find(id);
                if (role != null && (role.getIsSystem() || (role.getAdmins() != null && !role.getAdmins().isEmpty())))
                {
                    return Message.error("console.role.deleteExistNotAllowed", role.getName());
                }
            }
            roleService.delete(ids);
        }
        return SUCCESS_MESSAGE;
    }

}
