package com.sammyun.dao.impl.app;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.app.AppPosterDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.app.AppPoster;
import com.sammyun.entity.app.AppPoster.OperatingSystem;

/**
 * Poster * DaoImpl - 应用超市海报
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("appPosterDaoImpl")
public class AppPosterDaoImpl extends BaseDaoImpl<AppPoster, Long> implements
		AppPosterDao {

	@Override
	public Page<AppPoster> findPage(Pageable pageable, Boolean isOnline,
			OperatingSystem operatingSystem) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AppPoster> criteriaQuery = criteriaBuilder
				.createQuery(AppPoster.class);
		Root<AppPoster> root = criteriaQuery.from(AppPoster.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();

		if (isOnline != null) {
			restrictions = criteriaBuilder.and(
					restrictions,
					criteriaBuilder.or(criteriaBuilder.equal(
							root.get("isOnline"), isOnline)));
		}
		if (operatingSystem != null) {
			restrictions = criteriaBuilder.and(
					restrictions,
					criteriaBuilder.or(criteriaBuilder.equal(
							root.get("operatingSystem"), operatingSystem)));
		}
		criteriaQuery.where(restrictions);
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("createDate")));
		return super.findPage(criteriaQuery, pageable);
	}
}
