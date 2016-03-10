package com.sammyun.dao.impl.app;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.app.AppReviewDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.app.AppReview;

/**
 * Dao - 应用评论
 * 
 * @author tangchao
 * @version 1.0
 */
@Repository("appReviewDaoImpl")
public class AppReviewDaoImpl extends BaseDaoImpl<AppReview, Long> implements
		AppReviewDao {

	@Override
	public Page<AppReview> findPage(Boolean isShow, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AppReview> criteriaQuery = criteriaBuilder
				.createQuery(AppReview.class);
		Root<AppReview> root = criteriaQuery.from(AppReview.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (isShow != null) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.equal(root.get("isShow"), isShow));
		}
		criteriaQuery.where(restrictions);
		return super.findPage(criteriaQuery, pageable);
	}

}
