package com.sammyun.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.SystemSuggestDao;
import com.sammyun.entity.SystemSuggest;

@Repository("systemSuggestDaoImpl")
public class SystemSuggestDaoImpl extends BaseDaoImpl <SystemSuggest,Long> implements SystemSuggestDao
{

    @Override
    public Page<SystemSuggest> findByRealName(String realName, Pageable pageable)
    {
        // TODO Auto-generated method stub
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SystemSuggest> criteriaQuery = criteriaBuilder.createQuery(SystemSuggest.class);
        Root<SystemSuggest> root = criteriaQuery.from(SystemSuggest.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (realName != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(root.<String>get("member").<String>get("realName"), "%"+realName+"%"));
        }
        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery, pageable);
    }

}
