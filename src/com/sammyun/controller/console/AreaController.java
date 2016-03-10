/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Message;
import com.sammyun.entity.Area;
import com.sammyun.service.AreaService;

/**
 * Controller - 地区
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Controller("adminAreaController")
@RequestMapping("/console/area")
public class AreaController extends BaseController
{

    @Resource(name = "areaServiceImpl")
    private AreaService areaService;

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Long parentId, ModelMap model)
    {
        model.addAttribute("parent", areaService.find(parentId));
        model.addAttribute("menuId", Area.class.getSimpleName());
        return "/console/area/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Area area, Long parentId, RedirectAttributes redirectAttributes, ModelMap model)
    {
        area.setParent(areaService.find(parentId));
        if (!isValid(area))
        {
            return ERROR_VIEW;
        }
        area.setFullName(null);
        area.setTreePath(null);
        area.setChildren(null);
        area.setMembers(null);
        areaService.save(area);
        model.addAttribute("menuId", Area.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("area", areaService.find(id));
        model.addAttribute("menuId", Area.class.getSimpleName());
        return "/console/area/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Area area, RedirectAttributes redirectAttributes, ModelMap model)
    {
        if (!isValid(area))
        {
            return ERROR_VIEW;
        }
        areaService.update(area, "fullName", "treePath", "parent", "children", "members", "receivers", "orders",
                "deliveryCenters");
        model.addAttribute("menuId", Area.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Long parentId, ModelMap model)
    {
        Area parent = areaService.find(parentId);
        if (parent != null)
        {
            model.addAttribute("parent", parent);
            model.addAttribute("areas", new ArrayList<Area>(parent.getChildren()));
        }
        else
        {
            model.addAttribute("areas", areaService.findRoots());
        }
        model.addAttribute("menuId", Area.class.getSimpleName());
        return "/console/area/list";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long id)
    {
        areaService.delete(id);
        return SUCCESS_MESSAGE;
    }

}
