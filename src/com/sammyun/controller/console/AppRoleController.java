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
import com.sammyun.entity.app.AppRole;
import com.sammyun.service.app.AppRoleService;

/**
 * Controller - 应用角色
 * 


 */
@Controller("appRoleController")
@RequestMapping("/console/app_role")
public class AppRoleController extends BaseController
{

    @Resource(name = "appRoleServiceImpl")
    private AppRoleService appRoleService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", appRoleService.findPage(pageable));
        model.addAttribute("menuId","AppRole");
        return "/console/app_role/list";
    }
    
    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
    	model.addAttribute("menuId", "AppRole");
        return "/console/app_role/add";
    }
    
    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(AppRole role, RedirectAttributes redirectAttributes, ModelMap model)
    {
        if (!isValid(role))
        {
            return ERROR_VIEW;
        }
        role.setIsSystem(false);
        appRoleService.save(role);
        model.addAttribute("menuId","AppRole");
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }
    
    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("role", appRoleService.find(id));
        model.addAttribute("menuId", "AppRole");
        return "/console/app_role/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(AppRole role, RedirectAttributes redirectAttributes, ModelMap model)
    {
        if (!isValid(role))
        {
            return ERROR_VIEW;
        }
        AppRole pRole = appRoleService.find(role.getId());
        if (pRole == null || pRole.getIsSystem())
        {
            return ERROR_VIEW;
        }
        appRoleService.update(role, "isSystem", "admins");
        model.addAttribute("menuId", "AppRole");
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
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
                AppRole role = appRoleService.find(id);
                if (role != null && (role.getIsSystem() || (role.getApps() != null && !role.getApps().isEmpty())))
                {
                    return Message.error("console.role.deleteExistNotAllowed", role.getName());
                }
            }
            appRoleService.delete(ids);
        }
        return SUCCESS_MESSAGE;
    }
}
