/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.plugin.xgForIos;

import javax.annotation.Resource;

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
import com.sammyun.plugin.MessagePushPlugin;
import com.sammyun.service.PluginConfigService;

/**
 * Controller - 信鸽
 * 
 * @author xutianlong
 * @version [版本号, Apr 17, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("adminXgController")
@RequestMapping("/console/message_push_plugin/xg")
public class XgForIosController extends BaseController
{

    @Resource(name = "xgForIosPlugin")
    private XgForIosPlugin xgForIosPlugin;

    @Resource(name = "pluginConfigServiceImpl")
    private PluginConfigService pluginConfigService;

    /**
     * 安装
     */
    @RequestMapping(value = "/install", method = RequestMethod.POST)
    public @ResponseBody
    Message install()
    {
        if (!xgForIosPlugin.getIsInstalled())
        {
            PluginConfig pluginConfig = new PluginConfig();
            pluginConfig.setPluginId(xgForIosPlugin.getId());
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
        if (xgForIosPlugin.getIsInstalled())
        {
            PluginConfig pluginConfig = xgForIosPlugin.getPluginConfig();
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
        PluginConfig pluginConfig = xgForIosPlugin.getPluginConfig();
        model.addAttribute("pluginConfig", pluginConfig);
        model.addAttribute("menuId", MessagePushPlugin.class.getSimpleName());
        return "/com/sammyun/plugin/xgForIos/setting";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ModelMap model, String accessID, String secretKey, String validTime,String environmentConf, String description,
            @RequestParam(defaultValue = "false") Boolean isEnabled, Integer order,
            RedirectAttributes redirectAttributes)
    {
        PluginConfig pluginConfig = xgForIosPlugin.getPluginConfig();
        pluginConfig.setAttribute("accessID", accessID);
        pluginConfig.setAttribute("secretKey", secretKey);
        pluginConfig.setAttribute("validTime", validTime);
        pluginConfig.setAttribute("environmentConf", environmentConf);
        pluginConfig.setAttribute("description", description);
        pluginConfig.setIsEnabled(isEnabled);
        pluginConfig.setOrder(order);
        pluginConfigService.update(pluginConfig);
        model.addAttribute("menuId", MessagePushPlugin.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:/console/message_push_plugin/list.ct";
    }

}
