/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao;

import java.util.List;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.dict.DictSchool;

/**
 * Dao - 会员
 * 
 * @author Sencloud Team

 */
public interface MemberDao extends BaseDao<Member, Long>
{

    /**
     * 判断用户名是否存在
     * 
     * @param username 用户名(忽略大小写)
     * @return 用户名是否存在
     */
    boolean usernameExists(String username);

    /**
     * 判断E-mail是否存在
     * 
     * @param email E-mail(忽略大小写)
     * @return E-mail是否存在
     */
    boolean emailExists(String email);

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
     * 检查会员号是否被绑定了
     * 
     * @param previousVipCodel
     * @param vipCode
     * @return
     */
    boolean vipCodeUnique(String vipCode);


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

    List<Member> findByUserID(String username);

    /**
     * 根据mobile查找会员
     * 
     * @param mobile mobile(忽略大小写)
     * @return 会员，若不存在则返回null
     */
    List<Member> findListByMobile(String mobile);

    /**
     * 查找用户
     */
    List<String> findAllMember();
    
    /**
     * 根据班级和类型查询老师或者家长
     * @param dictSchool
     * @return
     */
    List<Member> findBySchoolAndType(DictSchool dictSchool,MemberType memberType);
    
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
     * 通过学校、分页信息、角色属性、是否启用查询教师
     * <功能详细描述>
     * @param dictSchool
     * @param pageable
     * @param memberType
     * @param isEnabled
     * @return
     * @see [类、类#方法、类#成员]
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
