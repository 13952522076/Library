package com.sammyun.dao.impl.app;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.app.AppUserDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.Member;
import com.sammyun.entity.app.App.OperatingSystem;
import com.sammyun.entity.app.App;
import com.sammyun.entity.app.AppUser;

/**
 * DaoImpl - 用户应用清单
 * 


 */
@Repository("appUserDaoImpl")
public class AppUserDaoImpl extends BaseDaoImpl<AppUser, Long> implements
		AppUserDao {

	@Override
	public Page<AppUser> findByMember(Member member, Boolean isDelete,
			OperatingSystem operatingSystem, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AppUser> criteriaQuery = criteriaBuilder
				.createQuery(AppUser.class);
		Root<AppUser> root = criteriaQuery.from(AppUser.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();

		if (member != null) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.equal(root.get("member"), member));
		}
		if (isDelete != null) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.equal(root.get("isDelete"), isDelete));
		}
		if (operatingSystem != null) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.notEqual(
							root.get("app").get("operatingSystem"),
							operatingSystem));
		}
		criteriaQuery.where(restrictions);
		return super.findPage(criteriaQuery, pageable);
	}
	
	@Override
	public AppUser findByParam(Member member, App app) {
		try {
			String jpql = "select appUsers from AppUser appUsers where lower(appUsers.member) = lower(:member) and lower(appUsers.app) = lower(:app)";
			return entityManager.createQuery(jpql, AppUser.class)
					.setFlushMode(FlushModeType.COMMIT)
					.setParameter("member", member).setParameter("app", app)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
