/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service;

import java.util.List;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.entity.Admin;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.dict.DictSchool;

/**
 * Service - 会员
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface MemberService extends BaseService<Member, Long>
{

    /**
     * 判断用户名是否存在
     * 
     * @param username 用户名(忽略大小写)
     * @return 用户名是否存在
     */
    boolean usernameExists(String username);

    /**
     * 检查会员号是否被绑定了
     * 
     * @param previousVipCodel
     * @param vipCode
     * @return
     */
    boolean vipCodeUnique(String previousVipCodel, String vipCode);
    
    /**
     * 判断用户名是否禁用
     * 
     * @param username 用户名(忽略大小写)
     * @return 用户名是否禁用
     */
    boolean usernameDisabled(String username);

    /**
     * 判断E-mail是否存在
     * 
     * @param email E-mail(忽略大小写)
     * @return E-mail是否存在
     */
    boolean emailExists(String email);

    /**
     * 判断E-mail是否唯一
     * 
     * @param previousEmail 修改前E-mail(忽略大小写)
     * @param currentEmail 当前E-mail(忽略大小写)
     * @return E-mail是否唯一
     */
    boolean emailUnique(String previousEmail, String currentEmail);

    /**
     * 判断mobile是否唯一
     * 
     * @param mobile
     * @return
     */
    boolean mobileUnique(String mobile);
    
    /**
     * 判断身份证是否唯一
     * 
     * @param idCard
     * @return
     * @see [类、类#方法、类#成员]
     */
    boolean idCardUnique(String idCard);

    /**
     * 保存会员
     * 
     * @param member 会员
     * @param operator 操作员
     */
    void save(Member member, Admin operator);

    /**
     * 更新会员
     * 
     * @param member 会员
     * @param modifyPoint 修改积分
     * @param modifyBalance 修改余额
     * @param depositMemo 修改余额备注
     * @param operator 操作员
     */
    void update(Member member, Integer modifyPoint, Admin operator);

    /**
     * 根据用户名查找会员
     * 
     * @param username 用户名(忽略大小写)
     * @return 会员，若不存在则返回null
     */
    Member findByUsername(String username);

    /**
     * 根据E-mail查找会员
     * 
     * @param email E-mail(忽略大小写)
     * @return 会员，若不存在则返回null
     */
    List<Member> findListByEmail(String email);

    /**
     * 判断会员是否登录
     * 
     * @return 会员是否登录
     */
    boolean isAuthenticated();

    /**
     * 获取当前登录会员
     * 
     * @return 当前登录会员，若不存在则返回null
     */
    Member getCurrent();

    /**
     * 获取当前登录用户名
     * 
     * @return 当前登录用户名，若不存在则返回null
     */
    String getCurrentUsername();



    /**
     * 根据用户名查找会员
     * 
     * @param username 用户名
     * @return 会员列表，若不存在则返回null
     */
    List<Member> findByUserID(String username);

    /**
     * 根据mobile查找会员
     * 
     * @param mobile mobile(忽略大小写)
     * @return 会员，若不存在则返回null
     */
    List<Member> findListByMobile(String mobile);


    /**
     * 判断mobile是否唯一
     * 
     * @param previousMobile 修改前mobile(忽略大小写)
     * @param mobile 当前mobile(忽略大小写)
     * @return mobile是否唯一
     */
    boolean mobileUnique(String previousMobile, String mobile);


    /**
     * 查找会员
     */
    List<String> findAllMember();
    
    /**
     * 根据班级和类型查询老师或者家长
     * @param dictSchool
     * @return
     */
    List<Member> findBySchoolAndType(DictSchool dictSchool,MemberType memberType);
    
    /**
     * 根据学校查询到相关的启用的老师
     * @param dictSchool
     * @return
     */
    List<Member> findActiveTeachersBySchool(DictSchool dictSchool);
    
    /**
     * 查找系统发件人
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Member>findSystemMember(MemberType memberType,DictSchool dictSchool);
    
    /**
     * 通过真实姓名查询出用户
     * <功能详细描述>
     * @param realName
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Member> findByRealName(String realName);
    
    /**
     * 根据学校 、分页信息、角色属性、是否启用查询到相关的启用的老师
     * @param dictSchool
     * @param pageable
     * @param memberType
     * @param isEnabled
     * @return
     */
    public Page<Member> findPage(DictSchool dictSchool,Pageable pageable,Member.MemberType memberType,Boolean isEnabled);

    /**
     * 根据姓名和学校模糊匹配查出老师
     * <功能详细描述>
     * @param realName
     * @param dictSchool
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Member> findTeacherByNameAndSchoolInLike(String realName,DictSchool dictSchool);
}
