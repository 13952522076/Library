/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.shop;

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
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sammyun.Message;
import com.sammyun.Principal;
import com.sammyun.Setting;
import com.sammyun.Setting.AccountLockType;
import com.sammyun.Setting.CaptchaType;
import com.sammyun.entity.BaseEntity.Save;
import com.sammyun.entity.Member;
import com.sammyun.entity.SafeKey;
import com.sammyun.service.CaptchaService;
import com.sammyun.service.MemberService;
import com.sammyun.service.RSAService;
import com.sammyun.util.EduUtil;
import com.sammyun.util.SettingUtils;
import com.sammyun.util.SpringUtils;
import com.sammyun.util.WebUtils;

/**
 * Controller - 会员登录
 * 


 */
@Controller("shopLoginController")
@RequestMapping("/login")
public class LoginController extends BaseController
{

    @Resource(name = "captchaServiceImpl")
    private CaptchaService captchaService;

    @Resource(name = "rsaServiceImpl")
    private RSAService rsaService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    /**
     * 登录检测
     */
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public @ResponseBody
    Boolean check()
    {
        return memberService.isAuthenticated();
    }

    /**
     * 登录页面
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(String redirectUrl, HttpServletRequest request, ModelMap model)
    {
        Setting setting = SettingUtils.get();
        if (redirectUrl != null && !redirectUrl.equalsIgnoreCase(setting.getSiteUrl())
                && !redirectUrl.startsWith(request.getContextPath() + "/")
                && !redirectUrl.startsWith(setting.getSiteUrl() + "/"))
        {
            redirectUrl = null;
        }
        model.addAttribute("redirectUrl", redirectUrl);
        model.addAttribute("captchaId", UUID.randomUUID().toString());
        return "/shop/login/index";
    }

    /**
     * 登录提交
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public @ResponseBody
    Message submit(String captchaId, String captcha, String username, HttpServletRequest request,
            HttpServletResponse response, HttpSession session)
    {
        String password = rsaService.decryptParameter("enPassword", request);
        rsaService.removePrivateKey(request);

        if (!captchaService.isValid(CaptchaType.memberLogin, captchaId, captcha))
        {
            return Message.error("shop.captcha.invalid");
        }
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            return Message.error("shop.common.invalid");
        }
        Member member;
        Setting setting = SettingUtils.get();
        if (setting.getIsEmailLogin() && username.contains("@"))
        {
            List<Member> members = memberService.findListByEmail(username);
            if (members.isEmpty())
            {
                member = null;
            }
            else if (members.size() == 1)
            {
                member = members.get(0);
            }
            else
            {
                return Message.error("shop.login.unsupportedAccount");
            }
        }
        else
        {
            member = memberService.findByUsername(username);
        }
        if (member == null)
        {
            return Message.error("shop.login.unknownAccount");
        }
        if (!member.getIsEnabled())
        {
            return Message.error("shop.login.disabledAccount");
        }
        checkLockedStatus(member, setting);

        if (!DigestUtils.md5Hex(password).equals(member.getPassword()))
        {
            int loginFailureCount = member.getLoginFailureCount() + 1;
            if (loginFailureCount >= setting.getAccountLockCount())
            {
                member.setIsLocked(true);
                member.setLockedDate(new Date());
            }
            member.setLoginFailureCount(loginFailureCount);
            memberService.update(member);
            if (ArrayUtils.contains(setting.getAccountLockTypes(), AccountLockType.member))
            {
                return Message.error("shop.login.accountLockCount", setting.getAccountLockCount());
            }
            else
            {
                return Message.error("shop.login.incorrectCredentials");
            }
        }
        updateLoginStatus(request, member);

       

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

        session.setAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME, new Principal(member.getId(), username));
        WebUtils.addCookie(request, response, Member.USERNAME_COOKIE_NAME, member.getUsername());

        return SUCCESS_MESSAGE;
    }

    /**
     * 登录提交
     */
    @RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
    public @ResponseBody
    Message loginSubmit(String username, HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        String password = rsaService.decryptParameter("enPassword", request);
        rsaService.removePrivateKey(request);

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            return Message.error("shop.common.invalid");
        }
        Member member;
        Setting setting = SettingUtils.get();
        if (setting.getIsEmailLogin() && username.contains("@"))
        {
            List<Member> members = memberService.findListByEmail(username);
            if (members.isEmpty())
            {
                member = null;
            }
            else if (members.size() == 1)
            {
                member = members.get(0);
            }
            else
            {
                return Message.error("shop.login.unsupportedAccount");
            }
        }
        else
        {
            member = memberService.findByUsername(username);
        }
        if (member == null)
        {
            return Message.error("shop.login.unknownAccount");
        }
        if (!member.getIsEnabled())
        {
            return Message.error("shop.login.disabledAccount");
        }
        boolean locked = checkLockedStatus(member, setting);
        if (locked)
        {
            return Message.error("shop.login.lockedAccount", setting.getAccountLockTime());
        }

        if (!DigestUtils.md5Hex(password).equals(member.getPassword()))
        {
            int loginFailureCount = member.getLoginFailureCount() + 1;
            if (loginFailureCount >= setting.getAccountLockCount())
            {
                member.setIsLocked(true);
                member.setLockedDate(new Date());
            }
            member.setLoginFailureCount(loginFailureCount);
            memberService.update(member);
            if (ArrayUtils.contains(setting.getAccountLockTypes(), AccountLockType.member))
            {
                return Message.error("shop.login.accountLockCount", setting.getAccountLockCount());
            }
            else
            {
                return Message.error("shop.login.incorrectCredentials");
            }
        }
        updateLoginStatus(request, member);

        syncCart(request, response, session, member);
        return SUCCESS_MESSAGE;
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param request
     * @param member
     * @see [类、类#方法、类#成员]
     */
    protected void updateLoginStatus(HttpServletRequest request, Member member)
    {
        member.setLoginIp(EduUtil.getAddr(request));
        member.setLoginDate(new Date());
        member.setLoginFailureCount(0);
        memberService.update(member);
    }

    /**
     * <一句话功能简述>如账户已锁定，需要判断锁定的时间是否超过后台配置的锁定时间<功能详细描述>
     * 
     * @param member
     * @param setting
     * @see [类、类#方法、类#成员]
     */
    protected boolean checkLockedStatus(Member member, Setting setting)
    {
        if (!member.getIsLocked())
        {
            return false;
        }
        boolean needUpdate = false;
        if (ArrayUtils.contains(setting.getAccountLockTypes(), AccountLockType.member))
        {
            int loginFailureLockTime = setting.getAccountLockTime();
            if (loginFailureLockTime == 0)
            {
                return false;
            }
            Date lockedDate = member.getLockedDate();
            Date unlockDate = DateUtils.addMinutes(lockedDate, loginFailureLockTime);
            if (new Date().after(unlockDate))
            {
                needUpdate = true;
            }
        }
        else
        {
            needUpdate = true;
        }
        if (needUpdate)
        {
            member.setLoginFailureCount(0);
            member.setIsLocked(false);
            member.setLockedDate(null);
            memberService.update(member);
            return false;
        }
        return true;
    }

    /**
     * <一句话功能简述>登陆后同步购物车 <功能详细描述>
     * 
     * @param username
     * @param request
     * @param response
     * @param session
     * @param member
     * @see [类、类#方法、类#成员]
     */
    protected void syncCart(HttpServletRequest request, HttpServletResponse response, HttpSession session, Member member)
    {

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
    }

   

    /**
     * 检查用户名
     */
    @RequestMapping(value = "/checkUsername", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> checkUsername(String userName)
    {
        Map<String, Object> data = new HashMap<String, Object>();
        Member member = memberService.findByUsername(userName);
        if (member == null)
        {
            data.put("returnCode", 1);
            data.put("returnMsg", SpringUtils.getMessage("console.validate.sms.phone.notexist"));
        }
        else
        {
            data.put("returnCode", 0);
        }
        return data;
    }

    

    /**
     * 重置密码
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    public String resetPassword(String username, String key, HttpServletRequest request, ModelMap model)
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
        model.addAttribute("username", username);
        model.addAttribute("member", member);
        model.addAttribute("key", key);
        return "/shop/login/resetPassword";
    }

    /**
     * 重置密码提交
     */
    @RequestMapping(value = "/resetPasswordSubmit", method = RequestMethod.POST)
    public @ResponseBody
    Message resetPasswordSubmit(Long uid, String key, String newPassword, HttpServletRequest request, ModelMap model)
    {
        Member member = memberService.find(uid);
        String password = rsaService.decryptParameter("newPassword", request);
        if (member == null)
        {
            return Message.warn("console.password.invalidMember");
        }
        if (!isValid(Member.class, "password", newPassword, Save.class))
        {
            return Message.warn("console.password.invalidPassword");
        }
        Setting setting = SettingUtils.get();
        if (password.length() < setting.getPasswordMinLength() || password.length() > setting.getPasswordMaxLength())
        {
            return Message.warn("console.password.invalidPassword");
        }
        SafeKey safeKey = member.getSafeKey();
        if (safeKey == null || safeKey.getValue() == null || !safeKey.getValue().equals(key))
        {
            return ERROR_MESSAGE;
        }
        if (safeKey.hasExpired())
        {
            return Message.error("console.password.hasExpired");
        }
        member.setPassword(DigestUtils.md5Hex(password));
        safeKey.setExpire(new Date());
        safeKey.setValue(null);
        memberService.update(member);
        return SUCCESS_MESSAGE;
    }
}
