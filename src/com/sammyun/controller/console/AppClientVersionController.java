/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Pageable;
import com.sammyun.entity.app.AppClientVersion;
import com.sammyun.service.app.AppClientVersionService;
import com.sammyun.uuid.UUIDHexGenerator;

/**
 * Controller - 终端版本
 * 
 * @author tangchao
 * @version 1.0
 */
@Controller("adminAppClientVersionController")
@RequestMapping("/console/app_client_version")
public class AppClientVersionController extends BaseController {

	@Resource(name = "appClientVersionServiceImpl")
	private AppClientVersionService appClientVersionService;

	/**
	 * 列表
	 * 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model,
			HttpServletRequest request) {
		model.addAttribute("page", appClientVersionService.findPage(pageable));
		model.addAttribute("menuId", "AppClientVersion");
		return "/console/app_client_version/list";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model, HttpServletRequest request) {
		model.addAttribute("menuId", "AppClientVersion");
		return "/console/app_client_version/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(AppClientVersion appClientVersion, HttpServletRequest request,
			RedirectAttributes redirectAttributes, ModelMap model) {
		appClientVersion.setFlagPublish(false);
		//给appClientVersion添加唯一的appId标识
		Boolean flag = false;
		String appId = "";
		do {
			appId = (String) UUIDHexGenerator.generate();
			flag = appClientVersionService.checkAppIdUnique(appId);
		} while (flag);
		appClientVersion.setAppId(appId);
		appClientVersionService.save(appClientVersion);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.ct";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		AppClientVersion appClientVersion = appClientVersionService.find(id);
		model.addAttribute("appClientVersion", appClientVersion);
		model.addAttribute("menuId", "AppClientVersion");
		return "/console/app_client_version/edit";
	}
	
	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(AppClientVersion appClientVersion, HttpServletRequest request,
			RedirectAttributes redirectAttributes, ModelMap model) {
		appClientVersionService.update(appClientVersion);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.ct";
	}
	
}
