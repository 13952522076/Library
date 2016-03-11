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
import com.sammyun.entity.Navigation;
import com.sammyun.entity.Navigation.Position;
import com.sammyun.service.NavigationService;

/**
 * Controller - 导航
 * 


 */
@Controller("adminNavigationController")
@RequestMapping("/console/navigation")
public class NavigationController extends BaseController
{

    @Resource(name = "navigationServiceImpl")
    private NavigationService navigationService;



    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        model.addAttribute("positions", Position.values());
        return "/console/navigation/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Navigation navigation, RedirectAttributes redirectAttributes)
    {
        if (!isValid(navigation))
        {
            return ERROR_VIEW;
        }
        navigationService.save(navigation);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("positions", Position.values());
        model.addAttribute("navigation", navigationService.find(id));
        return "/console/navigation/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Navigation navigation, RedirectAttributes redirectAttributes)
    {
        if (!isValid(navigation))
        {
            return ERROR_VIEW;
        }
        navigationService.update(navigation);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("topNavigations", navigationService.findList(Position.top));
        model.addAttribute("middleNavigations", navigationService.findList(Position.middle));
        model.addAttribute("bottomNavigations", navigationService.findList(Position.bottom));
        return "/console/navigation/list";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        navigationService.delete(ids);
        return SUCCESS_MESSAGE;
    }

}
