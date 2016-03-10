package com.sammyun.dao.impl.parenting;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.news.NewsDao;
import com.sammyun.dao.parenting.ParentingDao;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.news.News;
import com.sammyun.entity.news.NewsCategory;
import com.sammyun.entity.parenting.Parenting;
import com.sammyun.entity.parenting.ParentingCategory;

/**
 * Parenting * DaoImpl - 育儿数据
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("parentingDaoImpl")
public class ParentingDaoImpl extends BaseDaoImpl<Parenting, Long> implements ParentingDao
{

    @Override
    public Long findIsTopCountByCategory(ParentingCategory parentingCategory, DictSchool dictSchool)
    {
        if (parentingCategory == null)
        {
            return 0L;
        }
        String jpql = "select count(*) from Parenting parenting where parenting.parentingCategory = :parentingCategory  and parenting.isTop = true";
        Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "parentingCategory", parentingCategory).getSingleResult();
        return count;
    }

    @Override
    public List<Parenting> findBySchool(DictSchool dictSchool, Boolean isTop, Integer status, Integer categoryDefFlag,
            Integer categoryStatus)
    {
        if ((isTop == null) || (status == null) || (categoryDefFlag == null) || (categoryStatus == null))
        {
            return null;
        }
        try
        {
            String jpql = "select parenting from Parenting parenting where parenting.isTop = :isTop and parenting.status = :status and parenting.parentingCategory.defFlag = :categoryDefFlag and parenting.parentingCategory.status = :categoryStatus";
            return entityManager.createQuery(jpql, Parenting.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                    "isTop", isTop).setParameter("status", status).setParameter("categoryDefFlag", categoryDefFlag).setParameter(
                    "categoryStatus", categoryStatus).getResultList();
        }
        catch (NoResultException e)
        {
            return null;
        }

    }

    @Override
    public Page<Parenting> findByCategory(ParentingCategory parentingCategory, Boolean isTop, Integer status,
            Pageable pageable)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Parenting> criteriaQuery = criteriaBuilder.createQuery(Parenting.class);
        Root<Parenting> root = criteriaQuery.from(Parenting.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();

        if (parentingCategory != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("parentingCategory"), parentingCategory));
        }
        if (isTop != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isTop"), isTop));
        }
        if (status != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("status"), status));
        }
        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery, pageable);
    }

    @Override
    public Long findIsTopCount()
    {
        String jpql = "select count(*) from Parenting parenting where parenting.isTop = true";
        Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).getSingleResult();
        return count;
    }

    @Override
    public List<Parenting> findByCategory(ParentingCategory parentingCategory, Boolean isTop)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Parenting> criteriaQuery = criteriaBuilder.createQuery(Parenting.class);
        Root<Parenting> root = criteriaQuery.from(Parenting.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (parentingCategory != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("parentingCategory"), parentingCategory));
        }
        if (isTop != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isTop"), isTop));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

}
