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

import com.sammyun.plugin.PaymentPlugin;
import com.sammyun.service.PluginService;

/**
 * Controller - 支付插件
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Controller("adminPaymentPluginController")
@RequestMapping("/console/payment_plugin")
public class PaymentPluginController extends BaseController
{

    @Resource(name = "pluginServiceImpl")
    private PluginService pluginService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model)
    {
        model.addAttribute("paymentPlugins", pluginService.getPaymentPlugins());
        model.addAttribute("menuId", PaymentPlugin.class.getSimpleName());
        return "/console/payment_plugin/list";
    }

}
