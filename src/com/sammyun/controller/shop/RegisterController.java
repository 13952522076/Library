/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.shop;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sammyun.CommonAttributes;
import com.sammyun.Message;
import com.sammyun.Principal;
import com.sammyun.Setting;
import com.sammyun.Setting.CaptchaType;
import com.sammyun.entity.Area;
import com.sammyun.entity.BaseEntity.Save;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.Gender;
import com.sammyun.entity.MemberAttribute;
import com.sammyun.entity.MemberAttribute.Type;
import com.sammyun.service.AreaService;
import com.sammyun.service.CaptchaService;
import com.sammyun.service.MemberAttributeService;
import com.sammyun.service.MemberService;
import com.sammyun.service.RSAService;
import com.sammyun.util.EduUtil;
import com.sammyun.util.SettingUtils;
import com.sammyun.util.WebUtils;

/**
 * Controller - 会员注册
 * 


 */
@Controller("shopRegisterController")
@RequestMapping("/register")
public class RegisterController extends BaseController
{

    @Resource(name = "captchaServiceImpl")
    private CaptchaService captchaService;

    @Resource(name = "rsaServiceImpl")
    private RSAService rsaService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "memberAttributeServiceImpl")
    private MemberAttributeService memberAttributeService;

    @Resource(name = "areaServiceImpl")
    private AreaService areaService;

    /**
     * 注册模式
     */
    public enum RegMode
    {

        /** email */
        email,
        /** mobile */
        mobile
    }

    /**
     * 检查用户名是否被禁用或已存在
     */
    @RequestMapping(value = "/check_username", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkUsername(String username)
    {
        if (StringUtils.isEmpty(username))
        {
            return false;
        }
        if (memberService.usernameDisabled(username) || memberService.usernameExists(username))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 检查E-mail是否存在
     */
    @RequestMapping(value = "/check_email", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkEmail(String email, HttpServletResponse response)
    {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        if (StringUtils.isEmpty(email))
        {
            return false;
        }
        if (memberService.emailExists(email))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 检查E-mail是否存在
     */
    @RequestMapping(value = "/mobileUnique", method = RequestMethod.GET)
    public @ResponseBody
    boolean mobileUnique(String mobile, HttpServletResponse response)
    {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        if (StringUtils.isEmpty(mobile))
        {
            return false;
        }
        if (memberService.mobileUnique(mobile))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 注册页面
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model)
    {
        model.addAttribute("genders", Gender.values());
        model.addAttribute("captchaId", UUID.randomUUID().toString());
        return "/shop/register/index";
    }

    /**
     * 注册提交
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public @ResponseBody
    Message submit(String captchaId, String captcha, String username, String email, HttpServletRequest request,
            HttpServletResponse response, HttpSession session)
    {
        String password = rsaService.decryptParameter("enPassword", request);
        rsaService.removePrivateKey(request);

        if (!captchaService.isValid(CaptchaType.memberRegister, captchaId, captcha))
        {
            return Message.error("shop.captcha.invalid");
        }
        Setting setting = SettingUtils.get();
        if (!setting.getIsRegisterEnabled())
        {
            return Message.error("shop.register.disabled");
        }
        if (!isValid(Member.class, "username", username, Save.class)
                || !isValid(Member.class, "password", password, Save.class)
                || !isValid(Member.class, "email", email, Save.class))
        {
            return Message.error("shop.common.invalid");
        }
        if (username.length() < setting.getUsernameMinLength() || username.length() > setting.getUsernameMaxLength())
        {
            return Message.error("shop.common.invalid");
        }
        if (password.length() < setting.getPasswordMinLength() || password.length() > setting.getPasswordMaxLength())
        {
            return Message.error("shop.common.invalid");
        }
        if (memberService.usernameDisabled(username) || memberService.usernameExists(username))
        {
            return Message.error("shop.register.disabledExist");
        }
        if (!setting.getIsDuplicateEmail() && memberService.emailExists(email))
        {
            return Message.error("shop.register.emailExist");
        }
        Member member = new Member();
        List<MemberAttribute> memberAttributes = memberAttributeService.findList();
        for (MemberAttribute memberAttribute : memberAttributes)
        {
            String parameter = request.getParameter("memberAttribute_" + memberAttribute.getId());
            if (memberAttribute.getType() == Type.name || memberAttribute.getType() == Type.address
                    || memberAttribute.getType() == Type.zipCode || memberAttribute.getType() == Type.phone
                    || memberAttribute.getType() == Type.mobile || memberAttribute.getType() == Type.text
                    || memberAttribute.getType() == Type.select)
            {
                if (memberAttribute.getIsRequired() && StringUtils.isEmpty(parameter))
                {
                    return Message.error("shop.common.invalid");
                }
                member.setAttributeValue(memberAttribute, parameter);
            }
            else if (memberAttribute.getType() == Type.gender)
            {
                Gender gender = StringUtils.isNotEmpty(parameter) ? Gender.valueOf(parameter) : null;
                if (memberAttribute.getIsRequired() && gender == null)
                {
                    return Message.error("shop.common.invalid");
                }
                member.setGender(gender);
            }
            else if (memberAttribute.getType() == Type.birth)
            {
                try
                {
                    Date birth = StringUtils.isNotEmpty(parameter) ? DateUtils.parseDate(parameter,
                            CommonAttributes.DATE_PATTERNS) : null;
                    if (memberAttribute.getIsRequired() && birth == null)
                    {
                        return Message.error("shop.common.invalid");
                    }
                    member.setBirth(birth);
                }
                catch (ParseException e)
                {
                    return Message.error("shop.common.invalid");
                }
            }
            else if (memberAttribute.getType() == Type.area)
            {
                Area area = StringUtils.isNotEmpty(parameter) ? areaService.find(Long.valueOf(parameter)) : null;
                if (area != null)
                {
                    member.setArea(area);
                }
                else if (memberAttribute.getIsRequired())
                {
                    return Message.error("shop.common.invalid");
                }
            }
            else if (memberAttribute.getType() == Type.checkbox)
            {
                String[] parameterValues = request.getParameterValues("memberAttribute_" + memberAttribute.getId());
                List<String> options = parameterValues != null ? Arrays.asList(parameterValues) : null;
                if (memberAttribute.getIsRequired() && (options == null || options.isEmpty()))
                {
                    return Message.error("shop.common.invalid");
                }
                member.setAttributeValue(memberAttribute, options);
            }
        }
        member.setUsername(username.toLowerCase());
        member.setPassword(DigestUtils.md5Hex(password));
        member.setEmail(email);
        member.setPoint(setting.getRegisterPoint());
        member.setIsEnabled(true);
        member.setIsLocked(false);
        member.setLoginFailureCount(0);
        member.setLockedDate(null);
        member.setRegisterIp(EduUtil.getAddr(request));
        member.setLoginIp(EduUtil.getAddr(request));
        member.setLoginDate(new Date());
        member.setSafeKey(null);
        memberService.save(member);

        Map<String, Object> attributes = new HashMap<String, Object>();
        Enumeration<?> keys = session.getAttributeNames();
        while (keys.hasMoreElements())
        {
            String key = (String) keys.nextElement();
            attributes.put(key, session.getAttribute(key));
        }
        session.invalidate();
        session = request.getSession();
        for (Entry<String, Object> entry : attributes.entrySet())
        {
            session.setAttribute(entry.getKey(), entry.getValue());
        }

        session.setAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME, new Principal(member.getId(), member.getUsername()));
        WebUtils.addCookie(request, response, Member.USERNAME_COOKIE_NAME, member.getUsername());

        return Message.success("shop.register.success");
    }

    /**
     * 注册提交
     */
    @RequestMapping(value = "/registSubmit", method = RequestMethod.POST)
    public @ResponseBody
    Message registSubmit(String firstName, String givenName, String email, String mobile, RegMode regMode, Long areaId,
            boolean thirdPart, HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        String password = rsaService.decryptParameter("enPassword", request);
        rsaService.removePrivateKey(request);

        Setting setting = SettingUtils.get();
        if (!setting.getIsRegisterEnabled())
        {
            return Message.error("shop.register.disabled");
        }
        if (password.length() < setting.getPasswordMinLength())
        {
            return Message.error("shop.login.password.length.lessThan", setting.getPasswordMinLength());
        }
        if (password.length() > setting.getPasswordMaxLength())
        {
            return Message.error("shop.login.password.length.greater", setting.getPasswordMaxLength());
        }
        if (!setting.getIsDuplicateEmail() && memberService.emailExists(email))
        {
            return Message.error("shop.register.emailExist");
        }

        Member member = new Member();
        List<MemberAttribute> memberAttributes = memberAttributeService.findList();
        for (MemberAttribute memberAttribute : memberAttributes)
        {
            String parameter = request.getParameter("memberAttribute_" + memberAttribute.getId());
            if (memberAttribute.getType() == Type.name || memberAttribute.getType() == Type.address
                    || memberAttribute.getType() == Type.zipCode || memberAttribute.getType() == Type.phone
                    || memberAttribute.getType() == Type.mobile || memberAttribute.getType() == Type.text
                    || memberAttribute.getType() == Type.select)
            {
                if (memberAttribute.getIsRequired() && StringUtils.isEmpty(parameter))
                {
                    return Message.error("shop.common.invalid");
                }
                member.setAttributeValue(memberAttribute, parameter);
            }
            else if (memberAttribute.getType() == Type.gender)
            {
                Gender gender = StringUtils.isNotEmpty(parameter) ? Gender.valueOf(parameter) : null;
                if (memberAttribute.getIsRequired() && gender == null)
                {
                    return Message.error("shop.common.invalid");
                }
                member.setGender(gender);
            }
            else if (memberAttribute.getType() == Type.birth)
            {
                try
                {
                    Date birth = StringUtils.isNotEmpty(parameter) ? DateUtils.parseDate(parameter,
                            CommonAttributes.DATE_PATTERNS) : null;
                    if (memberAttribute.getIsRequired() && birth == null)
                    {
                        return Message.error("shop.common.invalid");
                    }
                    member.setBirth(birth);
                }
                catch (ParseException e)
                {
                    return Message.error("shop.common.invalid");
                }
            }
            else if (memberAttribute.getType() == Type.area)
            {
                Area area = StringUtils.isNotEmpty(parameter) ? areaService.find(Long.valueOf(parameter)) : null;
                if (area != null)
                {
                    member.setArea(area);
                }
                else if (memberAttribute.getIsRequired())
                {
                    return Message.error("shop.common.invalid");
                }
            }
            else if (memberAttribute.getType() == Type.checkbox)
            {
                String[] parameterValues = request.getParameterValues("memberAttribute_" + memberAttribute.getId());
                List<String> options = parameterValues != null ? Arrays.asList(parameterValues) : null;
                if (memberAttribute.getIsRequired() && (options == null || options.isEmpty()))
                {
                    return Message.error("shop.common.invalid");
                }
                member.setAttributeValue(memberAttribute, options);
            }
        }
        Area area = this.areaService.find(areaId);
        if (RegMode.email == regMode)
        {
            member.setUsername(email);
        }
        else if (RegMode.mobile == regMode)
        {
            member.setUsername(mobile);
        }
        else
        {
            member.setUsername(email);
        }
        member.setPassword(DigestUtils.md5Hex(password));
        member.setEmail(email);
        member.setMobile(mobile);
        member.setArea(area);
        member.setPoint(setting.getRegisterPoint());
        member.setIsEnabled(true);
        member.setIsLocked(false);
        member.setLoginFailureCount(0);
        member.setLockedDate(null);
        member.setRegisterIp(EduUtil.getAddr(request));
        member.setLoginIp(EduUtil.getAddr(request));
        member.setLoginDate(new Date());
        member.setSafeKey(null);
        member.setAddress(area.getFullName());
        memberService.save(member);

        Map<String, Object> attributes = new HashMap<String, Object>();
        Enumeration<?> keys = session.getAttributeNames();
        while (keys.hasMoreElements())
        {
            String key = (String) keys.nextElement();
            attributes.put(key, session.getAttribute(key));
        }
        session.invalidate();
        session = request.getSession();
        for (Entry<String, Object> entry : attributes.entrySet())
        {
            session.setAttribute(entry.getKey(), entry.getValue());
        }

        session.setAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME, new Principal(member.getId(), member.getUsername()));
        WebUtils.addCookie(request, response, Member.USERNAME_COOKIE_NAME, member.getUsername());
        return Message.success("shop.register.success");
    }
}
