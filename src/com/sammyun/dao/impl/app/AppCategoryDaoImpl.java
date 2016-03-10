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

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.app.AppCategoryDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.app.AppCategory;

/**
 * Dao - 应用分类
 * 
 * @author tangchao
 * @version 1.0
 */
@Repository("appCategoryDaoImpl")
public class AppCategoryDaoImpl extends BaseDaoImpl<AppCategory, Long>
		implements AppCategoryDao {
	/**
	 * 判断分类名称是否存在
	 * 
	 * @param name
	 *            分类名称(忽略大小写)
	 * @return 分类名称是否存在
	 */
	public boolean nameUnique(String name) {
		if (name == null) {
			return false;
		}
		String jpql = "select count(*) from AppCategory appCategorys where lower(appCategorys.name) = lower(:name)";
		Long count = entityManager.createQuery(jpql, Long.class)
				.setFlushMode(FlushModeType.COMMIT).setParameter("name", name)
				.getSingleResult();
		return count > 0;
	}

	@Override
	public Page<AppCategory> findPage(Boolean status, Pageable pageable) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AppCategory> criteriaQuery = criteriaBuilder
				.createQuery(AppCategory.class);
		Root<AppCategory> root = criteriaQuery.from(AppCategory.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (status != null) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.equal(root.get("status"), status));
		}
		criteriaQuery.where(restrictions);
		return super.findPage(criteriaQuery, pageable);
	}

	@Override
	public List<AppCategory> findList(Boolean status) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AppCategory> criteriaQuery = criteriaBuilder
				.createQuery(AppCategory.class);
		Root<AppCategory> root = criteriaQuery.from(AppCategory.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (status != null) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.equal(root.get("status"), status));
		}
		criteriaQuery.where(restrictions);
		return super.findList(criteriaQuery, null, null, null, null);
	}
}
