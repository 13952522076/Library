/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao.impl.ad;


import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.ad.AdDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.ad.Ad;
import com.sammyun.entity.ad.Ad.AdPosition;
import com.sammyun.entity.ad.Ad.Platform;
import com.sammyun.entity.ad.Ad.ShowType;
import com.sammyun.entity.ad.Ad.SimType;
import com.sammyun.entity.ad.Ad.Type;
import com.sammyun.entity.ad.AdArea;
import com.sammyun.entity.ad.AdCategory;
import com.sammyun.entity.ad.AdDeviceType;
import com.sammyun.entity.ad.AdNetType;
import com.sammyun.entity.ad.AdNetWork;
import com.sammyun.entity.ad.AdTimeSlot;
import com.sammyun.entity.dict.DictSchool;

/**
 * Dao - 广告
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("adDaoImpl")
public class AdDaoImpl extends BaseDaoImpl<Ad, Long> implements AdDao {

    @Override
    public Page<Ad> finPage(Type type, Platform platform,AdPosition adPosition, ShowType showType, SimType simType,
            AdCategory adCategory, Date date, List<AdArea> adAreas,
            List<AdDeviceType> adDeviceTypes, List<AdNetType> adNetTypes, List<AdNetWork> adNetWorks,
            List<AdTimeSlot> adTimeSlots, List<DictSchool> adDictSchools,Boolean isOnLine ,Boolean isDraft,Boolean status,Pageable pageable)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ad> criteriaQuery = criteriaBuilder.createQuery(Ad.class);
        Root<Ad> root = criteriaQuery.from(Ad.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        
        if (type != null)
        {
            restrictions = criteriaBuilder.and(restrictions, 
                    criteriaBuilder.equal(root.get("type"), type));
        }
        if (platform != null)
        {
            restrictions = criteriaBuilder.and(restrictions, 
                    criteriaBuilder.equal(root.get("platform"), platform));
        }
        if (adPosition != null)
        {
            restrictions = criteriaBuilder.and(restrictions, 
                    criteriaBuilder.equal(root.get("adPosition"), adPosition));
        }
        if (showType != null)
        {
            restrictions = criteriaBuilder.and(restrictions, 
                    criteriaBuilder.equal(root.get("showType"), showType));
        }
        if (simType != null)
        {
            restrictions = criteriaBuilder.and(restrictions, 
                    criteriaBuilder.equal(root.get("simType"), simType));
        }
        if (adCategory != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("adCategory"), adCategory));
        }
        if (date != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.lessThanOrEqualTo(root.<Date> get("beginDate"), date));
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.greaterThanOrEqualTo(root.<Date> get("endDate"), date));
        }
        
        if (adAreas != null && !adAreas.isEmpty())
        {
            Subquery<Ad> subquery = criteriaQuery.subquery(Ad.class);
            Root<Ad> subqueryRoot = subquery.from(Ad.class);
            subquery.select(subqueryRoot);
            subquery.where(criteriaBuilder.equal(subqueryRoot, root), subqueryRoot.join("adAreas").in(adAreas));
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.exists(subquery));
        }
        if (adDeviceTypes != null && !adDeviceTypes.isEmpty())
        {
            Subquery<Ad> subquery = criteriaQuery.subquery(Ad.class);
            Root<Ad> subqueryRoot = subquery.from(Ad.class);
            subquery.select(subqueryRoot);
            subquery.where(criteriaBuilder.equal(subqueryRoot, root), subqueryRoot.join("adDeviceTypes").in(adDeviceTypes));
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.exists(subquery));
        }
        if (adNetTypes != null && !adNetTypes.isEmpty())
        {
            Subquery<Ad> subquery = criteriaQuery.subquery(Ad.class);
            Root<Ad> subqueryRoot = subquery.from(Ad.class);
            subquery.select(subqueryRoot);
            subquery.where(criteriaBuilder.equal(subqueryRoot, root), subqueryRoot.join("adNetTypes").in(adNetTypes));
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.exists(subquery));
        }
        if (adNetWorks != null && !adNetWorks.isEmpty())
        {
            Subquery<Ad> subquery = criteriaQuery.subquery(Ad.class);
            Root<Ad> subqueryRoot = subquery.from(Ad.class);
            subquery.select(subqueryRoot);
            subquery.where(criteriaBuilder.equal(subqueryRoot, root), subqueryRoot.join("adNetWorks").in(adNetWorks));
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.exists(subquery));
        }
        if (adTimeSlots != null && !adTimeSlots.isEmpty())
        {
            Subquery<Ad> subquery = criteriaQuery.subquery(Ad.class);
            Root<Ad> subqueryRoot = subquery.from(Ad.class);
            subquery.select(subqueryRoot);
            subquery.where(criteriaBuilder.equal(subqueryRoot, root), subqueryRoot.join("adTimeSlots").in(adTimeSlots));
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.exists(subquery));
        }
        if (adDictSchools != null && !adDictSchools.isEmpty())
        {
            Subquery<Ad> subquery = criteriaQuery.subquery(Ad.class);
            Root<Ad> subqueryRoot = subquery.from(Ad.class);
            subquery.select(subqueryRoot);
            subquery.where(criteriaBuilder.equal(subqueryRoot, root), subqueryRoot.join("adDictSchools").in(adDictSchools));
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.exists(subquery));
        }
        if (isOnLine != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("isOnLine"), isOnLine));
        }
        if (isDraft != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("isDraft"), isDraft));
        }
        if (status != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("adCategory").get("status"), status));
        }
        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery, pageable);
    }

}