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

import com.sammyun.Template;
import com.sammyun.service.StaticService;
import com.sammyun.service.TemplateService;

/**
 * Controller - Sitemap
 * 
 * @author Sencloud Team

 */
@Controller("adminSitemapController")
@RequestMapping("/console/sitemap")
public class SitemapController extends BaseController
{

    @Resource(name = "templateServiceImpl")
    private TemplateService templateService;

    @Resource(name = "staticServiceImpl")
    private StaticService staticService;

    /**
     * 生成Sitemap
     */
    @RequestMapping(value = "/build", method = RequestMethod.GET)
    public String build(ModelMap model)
    {
        Template sitemapIndexTemplate = templateService.get("sitemapIndex");
        model.addAttribute("sitemapIndexPath", sitemapIndexTemplate.getStaticPath());
        model.addAttribute("menuId", "Sitemap");
        return "/console/sitemap/build";
    }

    /**
     * 生成Sitemap
     */
    @RequestMapping(value = "/build", method = RequestMethod.POST)
    public String build(RedirectAttributes redirectAttributes, ModelMap model)
    {
        staticService.buildSitemap();
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        model.addAttribute("menuId", "Sitemap");
        return "redirect:build.ct";
    }

}
