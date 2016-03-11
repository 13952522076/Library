/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.shop;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sammyun.Message;
import com.sammyun.Setting;
import com.sammyun.Setting.CaptchaType;
import com.sammyun.entity.BaseEntity.Save;
import com.sammyun.entity.Member;
import com.sammyun.entity.SafeKey;
import com.sammyun.service.CaptchaService;
import com.sammyun.service.MemberService;
import com.sammyun.util.SettingUtils;
import com.sammyun.util.SpringUtils;

/**
 * Controller - 密码
 * 


 */
@Controller("shopPasswordController")
@RequestMapping("/password")
public class PasswordController extends BaseController
{

    @Resource(name = "captchaServiceImpl")
    private CaptchaService captchaService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;


    /**
     * 找回密码
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(Model model)
    {
        model.addAttribute("captchaId", UUID.randomUUID().toString());
        return "/shop/password/find";
    }

    /**
     * 找回密码提交
     */
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> find(String username, String email)
    {
        Map<String, Object> data = new HashMap<String, Object>();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(email))
        {
            data.put("returnCode", 1);
            data.put("returnMessage", SpringUtils.getMessage("shop.common.invalid"));
            return data;
        }
        Member member = memberService.findByUsername(username);
        if (member == null)
        {
            data.put("returnCode", 1);
            data.put("returnMessage", SpringUtils.getMessage("shop.password.memberNotExist"));
            return data;
        }
        if (!member.getEmail().equalsIgnoreCase(email))
        {
            data.put("returnCode", 1);
            data.put("returnMessage", SpringUtils.getMessage("shop.password.invalidEmail"));
            return data;
        }
        Setting setting = SettingUtils.get();
        SafeKey safeKey = new SafeKey();
        safeKey.setValue(UUID.randomUUID().toString() + DigestUtils.md5Hex(RandomStringUtils.randomAlphabetic(30)));
        safeKey.setExpire(setting.getSafeKeyExpiryTime() != 0 ? DateUtils.addMinutes(new Date(),
                setting.getSafeKeyExpiryTime()) : null);
        member.setSafeKey(safeKey);
        memberService.update(member);
       
        data.put("returnCode", 0);
        data.put("returnMessage", SpringUtils.getMessage("shop.password.sentEmail"));
        return data;
    }

    /**
     * 重置密码
     */
    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public String reset(String username, String key, Model model)
    {
        Member member = memberService.findByUsername(username);
        if (member == null)
        {
            return ERROR_VIEW;
        }
        SafeKey safeKey = member.getSafeKey();
        if (safeKey == null || safeKey.getValue() == null || !safeKey.getValue().equals(key))
        {
            return ERROR_VIEW;
        }
        if (safeKey.hasExpired())
        {
            model.addAttribute("erroInfo", Message.warn("shop.password.hasExpired"));
            return ERROR_VIEW;
        }
        model.addAttribute("captchaId", UUID.randomUUID().toString());
        model.addAttribute("member", member);
        model.addAttribute("key", key);
        return "/shop/password/reset";
    }

    /**
     * 重置密码提交
     */
    @RequestMapping(value = "reset", method = RequestMethod.POST)
    public @ResponseBody
    Message reset(String captchaId, String captcha, String username, String newPassword, String key)
    {
        if (!captchaService.isValid(CaptchaType.resetPassword, captchaId, captcha))
        {
            return Message.error("shop.captcha.invalid");
        }
        Member member = memberService.findByUsername(username);
        if (member == null)
        {
            return ERROR_MESSAGE;
        }
        if (!isValid(Member.class, "password", newPassword, Save.class))
        {
            return Message.warn("shop.password.invalidPassword");
        }
        Setting setting = SettingUtils.get();
        if (newPassword.length() < setting.getPasswordMinLength()
                || newPassword.length() > setting.getPasswordMaxLength())
        {
            return Message.warn("shop.password.invalidPassword");
        }
        SafeKey safeKey = member.getSafeKey();
        if (safeKey == null || safeKey.getValue() == null || !safeKey.getValue().equals(key))
        {
            return ERROR_MESSAGE;
        }
        if (safeKey.hasExpired())
        {
            return Message.error("shop.password.hasExpired");
        }
        member.setPassword(DigestUtils.md5Hex(newPassword));
        safeKey.setExpire(new Date());
        safeKey.setValue(null);
        memberService.update(member);
        return Message.success("shop.password.resetSuccess");
    }

}
