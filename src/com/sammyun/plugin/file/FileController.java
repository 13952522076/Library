/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.plugin.file;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.controller.console.BaseController;
import com.sammyun.entity.PluginConfig;
import com.sammyun.plugin.StoragePlugin;
import com.sammyun.service.PluginConfigService;

/**
 * Controller - 本地文件存储
 * 
 * @author Sencloud Team

 */
@Controller("adminPluginFileController")
@RequestMapping("/console/storage_plugin/file")
public class FileController extends BaseController
{

    @Resource(name = "filePlugin")
    private FilePlugin filePlugin;

    @Resource(name = "pluginConfigServiceImpl")
    private PluginConfigService pluginConfigService;

    /**
     * 设置
     */
    @RequestMapping(value = "/setting", method = RequestMethod.GET)
    public String setting(ModelMap model)
    {
        PluginConfig pluginConfig = filePlugin.getPluginConfig();
        model.addAttribute("pluginConfig", pluginConfig);
        model.addAttribute("menuId", StoragePlugin.class.getSimpleName());
        return "/com/sammyun/plugin/file/setting";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ModelMap model,Integer order, RedirectAttributes redirectAttributes)
    {
        PluginConfig pluginConfig = filePlugin.getPluginConfig();
        pluginConfig.setIsEnabled(true);
        pluginConfig.setOrder(order);
        pluginConfigService.update(pluginConfig);
        model.addAttribute("menuId", StoragePlugin.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:/console/storage_plugin/list.ct";
    }

}
