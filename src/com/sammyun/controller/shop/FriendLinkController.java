/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.shop;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sammyun.entity.FriendLink.Type;
import com.sammyun.service.FriendLinkService;

/**
 * Controller - 友情链接
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Controller("shopFriendLinkController")
@RequestMapping("/friend_link")
public class FriendLinkController extends BaseController
{

    @Resource(name = "friendLinkServiceImpl")
    private FriendLinkService friendLinkService;

    /**
     * 首页
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model)
    {
        model.addAttribute("textFriendLinks", friendLinkService.findList(Type.text));
        model.addAttribute("imageFriendLinks", friendLinkService.findList(Type.image));
        return "/shop/friend_link/index";
    }

}
