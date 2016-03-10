/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sammyun.entity.Member;
import com.sammyun.util.WebUtils;

/**
 * Controller - 会员注销
 * 
 * @author Sencloud Team

 */
@Controller("shopLogoutController")
public class LogoutController extends BaseController
{

    /**
     * 注销
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        session.removeAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
        WebUtils.removeCookie(request, response, Member.USERNAME_COOKIE_NAME);
        return "redirect:/";
    }

}
