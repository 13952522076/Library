/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
import com.sammyun.entity.library.Collection;
import com.sammyun.entity.library.Mark;
import com.sammyun.service.AdminService;
import com.sammyun.service.RoleService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.util.JsonUtils;

/**
 * Controller - 管理员
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
     * 个人信息页面 <功能详细描述>
     * 
     * @param id
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info(Long id, ModelMap model)
    {
        Admin admin = adminService.getCurrent();
        model.addAttribute("admin", admin);
        model.addAttribute("menuId", "Admin");
        // 获取个人动态
        model.addAttribute("actions",personalAction(admin));
        return "/console/admin/info";
    }
    
    /**
     * 获取个人收藏，评论排名
     * <功能详细描述>
     * @param admin
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Float> actionRank(Admin admin){
        Map<String,Float> rankMap = new HashMap<String,Float>();
        
        
        
        
        return  null;
    }
    

    /**
     * 获取个人动态 <功能详细描述>
     * 
     * @param id
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Map<String, String>> personalAction(Admin admin)
    {
        Set<Mark> marks = admin.getMarks();
        Set<Collection> collections = admin.getCollections();
        List<Map<String, String>> actions = new ArrayList<Map<String, String>>();
        for (Mark mark : marks)
        {
            Map<String, String> actionMap = new HashMap<String, String>();
            actionMap.put("time", mark.getCreateDate().toString());
            actionMap.put("action", "评论");
            actionMap.put("bookId", mark.getBook().getId().toString());
            actionMap.put("evaluation", mark.getEvaluation());
            actionMap.put("bookName", mark.getBook().getName());
            actions.add(actionMap);
        }
        for (Collection collection : collections)
        {
            Map<String, String> actionMap = new HashMap<String, String>();
            actionMap.put("time", collection.getCreateDate().toString());
            actionMap.put("action", "收藏");
            actionMap.put("bookId", collection.getBook().getId().toString());
            actionMap.put("bookName", collection.getBook().getName());
            actions.add(actionMap);
        }
        Collections.sort(actions, new Comparator<Map<String, String>>()
        {
            public int compare(Map<String, String> action1, Map<String, String> action2)
            {
                return (action2.get("time")).compareTo(action1.get("time"));
            }
        });
        return actions;
    }

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
    public String save(Admin admin, Long dictSchoolId, Long[] roleIds, RedirectAttributes redirectAttributes,
            ModelMap model)
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
        admin.setLoginDate(null);
        admin.setLoginIp(null);
        adminService.save(admin);
        model.addAttribute("menuId", Admin.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);

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
    public String update(Admin admin, Long dictSchoolId, Long[] roleIds, RedirectAttributes redirectAttributes,
            ModelMap model)
    {
        DictSchool dictSchool = dictSchoolService.find(dictSchoolId);
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

    /**
     * ajax修改用户名，邮箱 <功能详细描述>
     * 
     * @param dictClassId
     * @param response
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/editInfo", method = RequestMethod.GET)
    public void editInfo(Long id, String name, String email, HttpServletResponse response)
    {
        Admin admin = adminService.find(id);
        admin.setName(name);
        admin.setEmail(email);
        adminService.update(admin);
        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), "success");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
