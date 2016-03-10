/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao.impl.announcement;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.announcement.AnnouncementDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.announcement.Announcement;
import com.sammyun.entity.dict.DictSchool;

/**
 * DaoImpl - 通知公告数据
 * 
 * @author Sencloud Team

 */
@Repository("announcementDaoImpl")
public class AnnouncementDaoImpl extends BaseDaoImpl<Announcement, Long> implements AnnouncementDao 
{

    @Override
    public Page<Announcement> findBySchool(DictSchool dictSchool, Integer status, Pageable pageable)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Announcement> criteriaQuery = criteriaBuilder.createQuery(Announcement.class);
        Root<Announcement> root = criteriaQuery.from(Announcement.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        
        if (dictSchool != null) {
          restrictions = criteriaBuilder.and(
                  restrictions,
                  criteriaBuilder.equal(
                          root.get("dictSchool"),dictSchool));
      }
      if (status != null) {
          restrictions = criteriaBuilder.and(
                  restrictions,
                  criteriaBuilder.equal(
                          root.get("status"), status));
      }
        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery, pageable);
    }

}
