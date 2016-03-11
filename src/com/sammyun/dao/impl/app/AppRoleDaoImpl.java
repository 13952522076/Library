package com.sammyun.dao.impl.app;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.app.AppRoleDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.app.AppRole;

/**
 * Dao - 应用角色
 * 


 */
@Repository("appRoleDaoImpl")
public class AppRoleDaoImpl extends BaseDaoImpl<AppRole, Long> implements AppRoleDao {

	@Override
	public List<AppRole> findList(List<MemberType> memberTypes) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AppRole> criteriaQuery = criteriaBuilder
				.createQuery(AppRole.class);
		Root<AppRole> root = criteriaQuery.from(AppRole.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (memberTypes != null) {
			Subquery<AppRole> subquery = criteriaQuery.subquery(AppRole.class);
			Root<AppRole> subqueryRoot = subquery.from(AppRole.class);
			subquery.select(subqueryRoot);
			subquery.where(criteriaBuilder.equal(subqueryRoot, root),
					subqueryRoot.join("memberTypes").in(memberTypes));
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.exists(subquery));
		}
		criteriaQuery.where(restrictions);
		return super.findList(criteriaQuery, null, null, null, null);
	}
}