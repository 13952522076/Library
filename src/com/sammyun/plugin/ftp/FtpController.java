/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.plugin.ftp;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Message;
import com.sammyun.controller.console.BaseController;
import com.sammyun.entity.PluginConfig;
import com.sammyun.plugin.PaymentPlugin;
import com.sammyun.plugin.StoragePlugin;
import com.sammyun.service.PluginConfigService;

/**
 * Controller - FTP
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Controller("adminPluginFtpController")
@RequestMapping("/console/storage_plugin/ftp")
public class FtpController extends BaseController
{

    @Resource(name = "ftpPlugin")
    private FtpPlugin ftpPlugin;

    @Resource(name = "pluginConfigServiceImpl")
    private PluginConfigService pluginConfigService;

    /**
     * 安装
     */
    @RequestMapping(value = "/install", method = RequestMethod.POST)
    public @ResponseBody
    Message install()
    {
        if (!ftpPlugin.getIsInstalled())
        {
            PluginConfig pluginConfig = new PluginConfig();
            pluginConfig.setPluginId(ftpPlugin.getId());
            pluginConfig.setIsEnabled(false);
            pluginConfigService.save(pluginConfig);
        }
        return SUCCESS_MESSAGE;
    }

    /**
     * 卸载
     */
    @RequestMapping(value = "/uninstall", method = RequestMethod.POST)
    public @ResponseBody
    Message uninstall()
    {
        if (ftpPlugin.getIsInstalled())
        {
            PluginConfig pluginConfig = ftpPlugin.getPluginConfig();
            pluginConfigService.delete(pluginConfig);
        }
        return SUCCESS_MESSAGE;
    }

    /**
     * 设置
     */
    @RequestMapping(value = "/setting", method = RequestMethod.GET)
    public String setting(ModelMap model)
    {
        PluginConfig pluginConfig = ftpPlugin.getPluginConfig();
        model.addAttribute("pluginConfig", pluginConfig);
        model.addAttribute("menuId", StoragePlugin.class.getSimpleName());
        return "/com/sammyun/plugin/ftp/setting";
    }
    
    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ModelMap model,String host, Integer port, String username, String password, String urlPrefix,
            @RequestParam(defaultValue = "false") Boolean isEnabled, Integer order,
            RedirectAttributes redirectAttributes)
    {
        PluginConfig pluginConfig = ftpPlugin.getPluginConfig();
        pluginConfig.setAttribute("host", host);
        pluginConfig.setAttribute("port", port.toString());
        pluginConfig.setAttribute("username", username);
        pluginConfig.setAttribute("password", password);
        pluginConfig.setAttribute("urlPrefix", StringUtils.removeEnd(urlPrefix, "/"));
        pluginConfig.setIsEnabled(isEnabled);
        pluginConfig.setOrder(order);
        pluginConfigService.update(pluginConfig);
        model.addAttribute("menuId", StoragePlugin.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:/console/storage_plugin/list.ct";
    }

}
