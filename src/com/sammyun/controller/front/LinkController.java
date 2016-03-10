/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.front;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sammyun.controller.shop.BaseController;
import com.sammyun.entity.FriendLink.Type;
import com.sammyun.service.FriendLinkService;

/**
 * Controller - 链接
 * 
 * @author Sencloud Team

 */
@Controller("linkController")
@RequestMapping("/front/link")
public class LinkController extends BaseController
{
    /**
     * 申请开通
     */
    @RequestMapping(value = "/applyOpen",method = RequestMethod.GET)
    public String applyOpen(ModelMap model)
    {
        return "/front/link/applyOpen";
    }
    
    /**
     * 使用帮助
     */
    @RequestMapping(value = "/help",method = RequestMethod.GET)
    public String help(ModelMap model)
    {
        return "/front/link/help";
    }
    
    /**
     * 使用协议
     */
    @RequestMapping(value = "/useAgreement",method = RequestMethod.GET)
    public String useAgreement(ModelMap model)
    {
        return "/front/link/useAgreement";
    }

}
