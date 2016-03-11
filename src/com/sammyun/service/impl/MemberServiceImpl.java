/*
 * Copyright 2012-2014 sencloud.com.cn. All rights reserved.
 * Support: http://www.sencloud.com.cn
 * License: http://www.sencloud.com.cn/license
 */
package com.sammyun.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.LockModeType;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.Principal;
import com.sammyun.Setting;
import com.sammyun.dao.MemberDao;
import com.sammyun.entity.Admin;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.MemberService;
import com.sammyun.util.SettingUtils;

/**
 * Service - 会员
 * 


 */
@Service("memberServiceImpl")
public class MemberServiceImpl extends BaseServiceImpl<Member, Long> implements MemberService
{

    @Resource(name = "memberDaoImpl")
    private MemberDao memberDao;

    @Resource(name = "memberDaoImpl")
    public void setBaseDao(MemberDao memberDao)
    {
        super.setBaseDao(memberDao);
    }

    /**
     * 判断用户名是否存在
     * 
     * @param username 用户名(忽略大小写)
     * @return 用户名是否存在
     */
    @Transactional(readOnly = true)
    public boolean usernameExists(String username)
    {
        return memberDao.usernameExists(username);
    }

    /**
     * 判断用户名是否禁用
     * 
     * @param username 用户名(忽略大小写)
     * @return 用户名是否禁用
     */
    @Transactional(readOnly = true)
    public boolean usernameDisabled(String username)
    {
        Assert.hasText(username);
        Setting setting = SettingUtils.get();
        if (setting.getDisabledUsernames() != null)
        {
            for (String disabledUsername : setting.getDisabledUsernames())
            {
                if (StringUtils.containsIgnoreCase(username, disabledUsername))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断E-mail是否存在
     * 
     * @param email E-mail(忽略大小写)
     * @return E-mail是否存在
     */
    @Transactional(readOnly = true)
    public boolean emailExists(String email)
    {
        return memberDao.emailExists(email);
    }

    /**
     * 判断E-mail是否唯一
     * 
     * @param previousEmail 修改前E-mail(忽略大小写)
     * @param currentEmail 当前E-mail(忽略大小写)
     * @return E-mail是否唯一
     */
    @Transactional(readOnly = true)
    public boolean emailUnique(String previousEmail, String currentEmail)
    {
        if (StringUtils.equalsIgnoreCase(previousEmail, currentEmail))
        {
            return true;
        }
        else
        {
            if (memberDao.emailExists(currentEmail))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    /**
     * 判断mobile是否唯一
     * 
     * @param mobile
     * @return
     */
    @Transactional(readOnly = true)
    public boolean mobileUnique(String mobile)
    {
        if (memberDao.mobileUnique(mobile))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 保存会员
     * 
     * @param member 会员
     * @param operator 操作员
     */
    public void save(Member member, Admin operator)
    {
        Assert.notNull(member);
        memberDao.persist(member);
    }

    /**
     * 更新会员
     * 
     * @param member 会员
     * @param modifyPoint 修改积分
     * @param modifyBalance 修改余额
     * @param depositMemo 修改余额备注
     * @param operator 操作员
     */
    public void update(Member member, Integer modifyPoint, Admin operator)
    {
        Assert.notNull(member);

        memberDao.lock(member, LockModeType.PESSIMISTIC_WRITE);

        if (modifyPoint != null && modifyPoint != 0 && member.getPoint() + modifyPoint >= 0)
        {
            member.setPoint(member.getPoint() + modifyPoint);
        }

        memberDao.merge(member);
    }

    /**
     * 根据用户名查找会员
     * 
     * @param username 用户名(忽略大小写)
     * @return 会员，若不存在则返回null
     */
    @Transactional(readOnly = true)
    public Member findByUsername(String username)
    {
        return memberDao.findByUsername(username);
    }

    /**
     * 根据E-mail查找会员
     * 
     * @param email E-mail(忽略大小写)
     * @return 会员，若不存在则返回null
     */
    @Transactional(readOnly = true)
    public List<Member> findListByEmail(String email)
    {
        return memberDao.findListByEmail(email);
    }

    /**
     * 判断会员是否登录
     * 
     * @return 会员是否登录
     */
    @Transactional(readOnly = true)
    public boolean isAuthenticated()
    {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes != null)
        {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            Principal principal = (Principal) request.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
            if (principal != null)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取当前登录会员
     * 
     * @return 当前登录会员，若不存在则返回null
     */
    @Transactional(readOnly = true)
    public Member getCurrent()
    {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes != null)
        {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            Principal principal = (Principal) request.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
            if (principal != null)
            {
                return memberDao.find(principal.getId());
            }
        }
        return null;
    }

    /**
     * 获取当前登录用户名
     * 
     * @return 当前登录用户名，若不存在则返回null
     */
    @Transactional(readOnly = true)
    public String getCurrentUsername()
    {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes != null)
        {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            Principal principal = (Principal) request.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
            if (principal != null)
            {
                return principal.getUsername();
            }
        }
        return null;
    }

    /**
     * 检查会员号是否被绑定了
     * 
     * @param previousVipCodel
     * @param vipCode
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public boolean vipCodeUnique(String previousVipCodel, String vipCode)
    {
        if (StringUtils.equalsIgnoreCase(previousVipCodel, vipCode))
        {
            return true;
        }
        else
        {
            if (memberDao.vipCodeUnique(vipCode))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
    }

    /**
     * 根据用户名查找会员
     * 
     * @param username 用户名
     * @return 会员列表，若不存在则返回null
     */
    @Override
    @Transactional(readOnly = true)
    public List<Member> findByUserID(String username)
    {
        return memberDao.findByUserID(username);
    }

    /**
     * 根据mobile查找会员
     * 
     * @param mobile mobile(忽略大小写)
     * @return 会员，若不存在则返回null
     */
    @Override
    public List<Member> findListByMobile(String mobile)
    {
        return memberDao.findListByMobile(mobile);
    }

    /**
     * 判断mobile是否唯一
     * 
     * @param previousMobile 修改前mobile(忽略大小写)
     * @param mobile 当前mobile(忽略大小写)
     * @return mobile是否唯一
     */
    @Override
    public boolean mobileUnique(String previousMobile, String mobile)
    {
        if (StringUtils.equalsIgnoreCase(previousMobile, mobile))
        {
            return true;
        }
        else
        {
            if (memberDao.mobileUnique(mobile))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    /**
     * 查找会员
     */
    @Override
    public List<String> findAllMember()
    {
        return memberDao.findAllMember();
    }

    /**
     * 判断身份证是否存在
     * 
     * @param idCard
     * @return
     */
    @Override
    public boolean idCardUnique(String idCard)
    {
        if (memberDao.idCardUnique(idCard))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public List<Member> findBySchoolAndType(DictSchool dictSchool, MemberType memberType)
    {
        // TODO Auto-generated method stub
        return memberDao.findBySchoolAndType(dictSchool, memberType);
    }

    @Override
    public List<Member> findActiveTeachersBySchool(DictSchool dictSchool)
    {
        if (dictSchool == null)
        {
            return null;
        }
        Set<Member> members = dictSchool.getMembers();
        if (members == null)
        {
            return null;
        }
        if (members.size() == 0)
        {
            return null;
        }
        List<Member> activeTeachers = new ArrayList<Member>();
        for (Member member : members)
        {
            if (member.getIsEnabled() == true && member.getMemberType() == MemberType.teacher)
            {
                activeTeachers.add(member);
            }
        }
        if (activeTeachers.size() == 0)
        {
            return null;
        }
        else
        {
            return activeTeachers;
        }
    }

    @Override
    public List<Member> findSystemMember(MemberType memberType,DictSchool dictSchool)
    {
        return memberDao.findSystemMember(memberType,dictSchool);
    }

    @Override
    public List<Member> findByRealName(String realName)
    {
        return memberDao.findByRealName(realName);
    }
    
    @Override
    public Page<Member> findPage(DictSchool dictSchool,Pageable pageable,Member.MemberType memberType,Boolean isEnabled)
    {
    	return memberDao.findPage(dictSchool,pageable,memberType,isEnabled);
    }

    @Override
    public List<Member> findTeacherByNameAndSchoolInLike(String realName, DictSchool dictSchool)
    {
        return memberDao.findTeacherByNameAndSchoolInLike(realName, dictSchool);
    }
}
