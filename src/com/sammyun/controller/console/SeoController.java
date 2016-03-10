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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Pageable;
import com.sammyun.entity.Seo;
import com.sammyun.service.SeoService;

/**
 * Controller - SEO设置
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Controller("adminSeoController")
@RequestMapping("/console/seo")
public class SeoController extends BaseController
{

    @Resource(name = "seoServiceImpl")
    private SeoService seoService;

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("seo", seoService.find(id));
        model.addAttribute("menuId", Seo.class.getSimpleName());
        return "/console/seo/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Seo seo, RedirectAttributes redirectAttributes,ModelMap model)
    {
        if (!isValid(seo))
        {
            return ERROR_VIEW;
        }
        seoService.update(seo, "type");
        model.addAttribute("menuId", Seo.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", seoService.findPage(pageable));
        model.addAttribute("menuId", Seo.class.getSimpleName());
        return "/console/seo/list";
    }

}
