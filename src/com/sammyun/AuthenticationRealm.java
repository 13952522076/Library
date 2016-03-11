/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.sammyun.Setting.AccountLockType;
import com.sammyun.Setting.CaptchaType;
import com.sammyun.entity.Admin;
import com.sammyun.service.AdminService;
import com.sammyun.service.CaptchaService;
import com.sammyun.util.SettingUtils;

/**
 * 权限认证
 * 


 */
public class AuthenticationRealm extends AuthorizingRealm
{

    @Resource(name = "captchaServiceImpl")
    private CaptchaService captchaService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    /**
     * 获取认证信息
     * 
     * @param token 令牌
     * @return 认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token)
    {
        AuthenticationToken authenticationToken = (AuthenticationToken) token;
        String username = authenticationToken.getUsername();
        String password = new String(authenticationToken.getPassword());
        String ip = authenticationToken.getHost();
        
        if (username != null && password != null)
        {
            Admin admin = adminService.findByUsername(username);
            if (admin == null)
            {
                throw new UnknownAccountException();
            }
            if (!password.equals(admin.getPassword()))
            {
                throw new IncorrectCredentialsException();
            }
            admin.setLoginIp(ip);
            admin.setLoginDate(new Date());
            adminService.update(admin);
            return new SimpleAuthenticationInfo(new Principal(admin.getId(), username), password, getName());
        }
        throw new UnknownAccountException();
    }

    /**
     * 获取授权信息
     * 
     * @param principals principals
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        Principal principal = (Principal) principals.fromRealm(getName()).iterator().next();
        if (principal != null)
        {
            List<String> authorities = adminService.findAuthorities(principal.getId());
            if (authorities != null)
            {
                SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
                authorizationInfo.addStringPermissions(authorities);
                return authorizationInfo;
            }
        }
        return null;
    }

}
