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

import com.sammyun.plugin.StoragePlugin;
import com.sammyun.service.PluginService;

/**
 * Controller - 存储插件
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Controller("adminStoragePluginController")
@RequestMapping("/console/storage_plugin")
public class StoragePluginController extends BaseController
{

    @Resource(name = "pluginServiceImpl")
    private PluginService pluginService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model)
    {
        model.addAttribute("storagePlugins", pluginService.getStoragePlugins());
        model.addAttribute("menuId", StoragePlugin.class.getSimpleName());
        return "/console/storage_plugin/list";
    }

}
