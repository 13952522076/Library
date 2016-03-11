package com.sammyun.dao.impl.poster;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.poster.PosterDao;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.news.NewsCategory;
import com.sammyun.entity.poster.Poster;
import com.sammyun.entity.poster.Poster.PosterPosition;

/**
 * Poster * DaoImpl - 海报
 * 


 */
@Repository("posterDaoImpl")
public class PosterDaoImpl extends BaseDaoImpl<Poster, Long> implements PosterDao  {

    @Override
    public List<Poster> findBySchool(DictSchool dictSchool, PosterPosition posterPosition, Boolean status)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Poster> criteriaQuery = criteriaBuilder.createQuery(Poster.class);
        Root<Poster> root = criteriaQuery.from(Poster.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        
        if (dictSchool != null) {
          restrictions = criteriaBuilder.and(
                  restrictions,
                  criteriaBuilder.equal(
                          root.get("dictSchool"),dictSchool));
      }
      if (posterPosition != null) {
          restrictions = criteriaBuilder.and(
                  restrictions,
                  criteriaBuilder.equal(
                          root.get("posterPosition"), posterPosition));
      }
      if (status != null) {
          restrictions = criteriaBuilder.and(
                  restrictions,
                  criteriaBuilder.equal(
                          root.get("status"), status));
      }
        criteriaQuery.where(restrictions);
        return super.findList(criteriaQuery, null, null, null, null);
    }

	@Override
	public List<Poster> findBySchool(DictSchool dictSchool,
			PosterPosition posterPosition, Boolean status, Date updateDate) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Poster> criteriaQuery = criteriaBuilder
				.createQuery(Poster.class);
		Root<Poster> root = criteriaQuery.from(Poster.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();

		if (dictSchool != null) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.equal(root.get("dictSchool"), dictSchool));
		}
		if (posterPosition != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder
					.equal(root.get("posterPosition"), posterPosition));
		}
		if (status != null) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.equal(root.get("status"), status));
		}
		if (updateDate != null) {
			restrictions = criteriaBuilder.and(
					restrictions,
					criteriaBuilder.greaterThanOrEqualTo(
							root.<Date> get("modifyDate"), updateDate));
		}
		criteriaQuery.where(restrictions);
		return super.findList(criteriaQuery, null, null, null, null);
	}

}
