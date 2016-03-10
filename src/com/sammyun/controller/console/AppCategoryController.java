/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Message;
import com.sammyun.Pageable;
import com.sammyun.entity.app.AppCategory;
import com.sammyun.service.app.AppCategoryService;

/**
 * Controller - 应用分类
 * 
 * @author tangchao
 * @version 1.0
 */
@Controller("adminAppCategoryController")
@RequestMapping("/console/app_category")
public class AppCategoryController extends BaseController {

	@Resource(name = "appCategoryServiceImpl")
	private AppCategoryService appCategoryService;

	/**
	 * 列表
	 * 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model,
			HttpServletRequest request) {
		model.addAttribute("page", appCategoryService.findPage(pageable));
		model.addAttribute("menuId", "AppCategory");
		return "/console/app_category/list";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model, HttpServletRequest request) {
		model.addAttribute("menuId", "AppCategory");
		return "/console/app_category/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(AppCategory appCategory, HttpServletRequest request,
			RedirectAttributes redirectAttributes, ModelMap model) {
		appCategory.setStatus(true);
		appCategoryService.save(appCategory);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.ct";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		AppCategory appCategory = appCategoryService.find(id);
		model.addAttribute("appCategory", appCategory);
		model.addAttribute("menuId", "AppCategory");
		return "/console/app_category/edit";
	}
	
	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(AppCategory appCategory, HttpServletRequest request,
			RedirectAttributes redirectAttributes, ModelMap model) {
		appCategoryService.update(appCategory);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.ct";
	}
	
	/**
	 * 禁用
	 */
	@RequestMapping(value = "/ban", method = RequestMethod.POST)
	public @ResponseBody
	Message ban(Long id, ModelMap model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		AppCategory appCategory = appCategoryService.find(id);
		if(appCategory.getApps() != null && appCategory.getApps().size() > 0){
			return Message.warn("该分类下存在应用，请确认！");
		}
		appCategory.setStatus(false);
		appCategoryService.update(appCategory);
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 启用
	 */
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public @ResponseBody
	Message start(Long id, ModelMap model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		AppCategory appCategory = appCategoryService.find(id);
		appCategory.setStatus(true);
		appCategoryService.update(appCategory);
		return SUCCESS_MESSAGE;
	}
	
    /**
     * 检查应用分类名称是否唯一
     */
    @RequestMapping(value = "/check_name", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkCode(String previousName, String name)
    {
        if (StringUtils.isEmpty(name))
        {
            return false;
        }
        if (appCategoryService.nameUnique(previousName, name))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
