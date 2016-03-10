/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sammyun.Principal;
import com.sammyun.dao.AdminDao;
import com.sammyun.entity.Admin;
import com.sammyun.entity.Role;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.AdminService;

/**
 * Service - 管理员
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("adminServiceImpl")
public class AdminServiceImpl extends BaseServiceImpl<Admin, Long> implements AdminService
{

    @Resource(name = "adminDaoImpl")
    private AdminDao adminDao;

    @Resource(name = "adminDaoImpl")
    public void setBaseDao(AdminDao adminDao)
    {
        super.setBaseDao(adminDao);
    }

    @Transactional(readOnly = true)
    public boolean usernameExists(String username)
    {
        return adminDao.usernameExists(username);
    }

    @Transactional(readOnly = true)
    public Admin findByUsername(String username)
    {
        return adminDao.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public List<String> findAuthorities(Long id)
    {
        List<String> authorities = new ArrayList<String>();
        Admin admin = adminDao.find(id);
        if (admin != null)
        {
            for (Role role : admin.getRoles())
            {
                authorities.addAll(role.getAuthorities());
            }
        }
        return authorities;
    }

    @Transactional(readOnly = true)
    public boolean isAuthenticated()
    {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null)
        {
            return subject.isAuthenticated();
        }
        return false;
    }

    @Transactional(readOnly = true)
    public Admin getCurrent()
    {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null)
        {
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null)
            {
                return adminDao.find(principal.getId());
            }
        }
        return null;
    }

    @Transactional(readOnly = true)
    public String getCurrentUsername()
    {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null)
        {
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null)
            {
                return principal.getUsername();
            }
        }
        return null;
    }

    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public void save(Admin admin)
    {
        super.save(admin);
    }

    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public Admin update(Admin admin)
    {
        return super.update(admin);
    }

    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public Admin update(Admin admin, String... ignoreProperties)
    {
        return super.update(admin, ignoreProperties);
    }

    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public void delete(Long id)
    {
        super.delete(id);
    }

    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public void delete(Long... ids)
    {
        super.delete(ids);
    }

    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public void delete(Admin admin)
    {
        super.delete(admin);
    }

    @Override
    public DictSchool getSchoolByAdmin(Admin admin)
    {
        DictSchool dictSchool = adminDao.getSchoolByAdmin(admin);
        return dictSchool;
    }

    /**
     * 获取当前管理员所在学校
     * 
     * @return 当前登录管理员,若不存在则返回null
     */
    @Override
    public DictSchool getCurrentDictSchool()
    {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null)
        {
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null)
            {
                Admin admin = adminDao.find(principal.getId());
                if (admin != null)
                {
                    return admin.getDictSchool();
                }
                else
                {
                    return null;
                }

            }
        }
        return null;
    }
}
