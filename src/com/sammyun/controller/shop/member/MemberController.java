/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.shop.member;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sammyun.controller.shop.BaseController;
import com.sammyun.entity.Member;
import com.sammyun.service.MemberService;
import com.sammyun.service.RSAService;
import com.sammyun.service.message.MessageService;

/**
 * Controller - 会员中心
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Controller("shopMemberController")
@RequestMapping("/member")
public class MemberController extends BaseController
{

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "messageServiceImpl")
    private MessageService messageService;

    @Resource(name = "rsaServiceImpl")
    private RSAService rsaService;

    /**
     * 首页
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Integer pageNumber, ModelMap model)
    {
        Member member = memberService.getCurrent();
        model.addAttribute("messageCount", messageService.count(member, false));
        return "shop/member/index";
    }
}
