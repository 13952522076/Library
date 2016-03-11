/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.MemberDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.Tag;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.parenting.Parenting;

/**
 * Dao - 会员
 * 


 */
@Repository("memberDaoImpl")
public class MemberDaoImpl extends BaseDaoImpl<Member, Long> implements MemberDao
{
    /**
     * @param username
     * @return true存在 false不存在
     */
    public boolean usernameExists(String username)
    {
        if (username == null)
        {
            return false;
        }
        String jpql = "select count(*) from Member members where lower(members.username) = lower(:username)";
        Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "username", username).getSingleResult();
        return count > 0;
    }

    public boolean emailExists(String email)
    {
        if (email == null)
        {
            return false;
        }
        String jpql = "select count(*) from Member members where lower(members.email) = lower(:email)";
        Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "email", email).getSingleResult();
        return count > 0;
    }

    public Member findByUsername(String username)
    {
        if (username == null)
        {
            return null;
        }
        try
        {
            String jpql = "select members from Member members where lower(members.username) = lower(:username) or lower(members.email) = lower(:username) or lower(members.mobile) = lower(:username)";
            return entityManager.createQuery(jpql, Member.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                    "username", username).setParameter("username", username).setParameter("username", username).getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

    public List<Member> findListByEmail(String email)
    {
        if (email == null)
        {
            return Collections.<Member> emptyList();
        }
        String jpql = "select members from Member members where lower(members.email) = lower(:email)";
        return entityManager.createQuery(jpql, Member.class).setFlushMode(FlushModeType.COMMIT).setParameter("email",
                email).getResultList();
    }

    /**
     * 检查会员号是否被绑定了
     * 
     * @param previousVipCodel
     * @param vipCode
     * @return
     */
    @Override
    public boolean vipCodeUnique(String vipCode)
    {
        if (vipCode == null)
        {
            return false;
        }
        String jpql = "select count(*) from Member members where lower(members.vipCode) = lower(:vipCode)";
        Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "vipCode", vipCode).getSingleResult();
        return count > 0;
    }

    /**
     * 判断mobile是否唯一
     * 
     * @param mobile
     * @return
     */
    @Override
    public boolean mobileUnique(String mobile)
    {
        if (mobile == null)
        {
            return false;
        }
        String jpql = "select count(*) from Member members where lower(members.mobile) = lower(:mobile)";
        Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "mobile", mobile).getSingleResult();
        return count > 0;
    }

    @Override
    public List<Member> findByUserID(String username)
    {
        if (username == null)
        {
            return Collections.<Member> emptyList();
        }
        String jpql = "select members from Member members where members.username like :username";
        return entityManager.createQuery(jpql, Member.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "username", "%" + username + "%").getResultList();
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
        if (mobile == null)
        {
            return Collections.<Member> emptyList();
        }
        String jpql = "select members from Member members where lower(members.mobile) = lower(:mobile)";
        return entityManager.createQuery(jpql, Member.class).setFlushMode(FlushModeType.COMMIT).setParameter("mobile",
                mobile).getResultList();
    }

    @Override
    public List<String> findAllMember()
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<Member> member = criteriaQuery.from(Member.class);

        criteriaQuery.multiselect(member.get("username"));
        Predicate restrictions = criteriaBuilder.conjunction();
        criteriaQuery.where(restrictions);
        criteriaQuery.groupBy(member.get("username"));
        TypedQuery<String> query = entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT);
        return query.getResultList();
    }

    /**
     * 判断身份证是否唯一
     * 
     * @param idCard
     * @return
     */
    @Override
    public boolean idCardUnique(String idCard)
    {
        if (idCard == null)
        {
            return false;
        }
        String jpql = "select count(*) from Member members where lower(members.idCard) = lower(:idCard)";
        Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "idCard", idCard).getSingleResult();
        return count > 0;
    }

    @Override
    public List<Member> findBySchoolAndType(DictSchool dictSchool, MemberType memberType)
    {
        if ((memberType == null) || (dictSchool == null))
        {
            return Collections.<Member> emptyList();
        }
        String jpql = "select members from Member members where members.memberType = :memberType and members.dictSchool = :dictSchool";
        return entityManager.createQuery(jpql, Member.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "memberType", memberType).setParameter("dictSchool", dictSchool).getResultList();
    }

    @Override
    public List<Member> findSystemMember(MemberType memberType, DictSchool dictSchool)
    {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
        Root<Member> root = criteriaQuery.from(Member.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();

        if (dictSchool != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("dictSchool"), dictSchool));
        }
        if (memberType != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("memberType"), memberType));
        }
        criteriaQuery.where(restrictions);
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createDate")));
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

    @Override
    public List<Member> findByRealName(String realName)
    {
        if (realName == null)
        {
            return Collections.<Member> emptyList();
        }
        String jpql = "select members from Member members where members.realName like :realName";
        return entityManager.createQuery(jpql, Member.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "realName", "%" + realName + "%").getResultList();
    }

    @Override
    public Page<Member> findPage(DictSchool dictSchool, Pageable pageable, Member.MemberType memberType,
            Boolean isEnabled)
    {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
        Root<Member> root = criteriaQuery.from(Member.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (dictSchool != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("dictSchool"), dictSchool));
        }
        if (memberType != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("memberType"), memberType));
        }
        if (isEnabled != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isEnabled"), isEnabled));
        }
        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery, pageable);
    }

    @Override
    public List<Member> findTeacherByNameAndSchoolInLike(String realName, DictSchool dictSchool)
    {
        if (realName == null || realName.equals("") || dictSchool == null)
        {
            return Collections.<Member> emptyList();
        }
        String jpql = "select members from Member members where members.realName like :realName and members.dictSchool = :dictSchool and members.memberType = :memberType";
        return entityManager.createQuery(jpql, Member.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "realName", "%" + realName + "%").setParameter("dictSchool", dictSchool).setParameter("memberType",
                MemberType.teacher).getResultList();
    }
}
