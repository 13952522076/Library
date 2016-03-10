/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao.impl.app;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.app.AppClientVersionDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.app.AppClientVersion.OperatingSystem;
import com.sammyun.entity.app.AppClientVersion;

/**
 * Dao - 终端版本
 * 
 * @author tangchao
 * @version 1.0
 */
@Repository("appClientVersionDaoImpl")
public class AppClientVersionDaoImpl extends
		BaseDaoImpl<AppClientVersion, Long> implements AppClientVersionDao {

	@Override
	public List<AppClientVersion> findByOperatingSystem(
			OperatingSystem operatingSystem) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AppClientVersion> criteriaQuery = criteriaBuilder
				.createQuery(AppClientVersion.class);
		Root<AppClientVersion> root = criteriaQuery
				.from(AppClientVersion.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (operatingSystem != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder
					.equal(root.get("operatingSystem"), operatingSystem));
		}
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createDate")));
		criteriaQuery.where(restrictions);
		return super.findList(criteriaQuery, null, null, null, null);
	}

	@Override
	public Boolean checkAppIdUnique(String appId) {
		if ("".equals(appId)) {
			return false;
		}
		String jpql = "select count(*) from AppClientVersion appClientVersion where appClientVersion.appId = :appId";
		Long count = entityManager.createQuery(jpql, Long.class)
				.setFlushMode(FlushModeType.COMMIT)
				.setParameter("appId", appId).getSingleResult();
		return count > 0;
	}

}
