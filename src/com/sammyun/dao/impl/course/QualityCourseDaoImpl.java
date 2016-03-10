package com.sammyun.dao.impl.course;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.Order;
import com.sammyun.dao.course.QualityCourseDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.course.QualityCourse;
import com.sammyun.entity.dict.DictSchool;

@Repository("qualityCourseDaoImpl")
public class QualityCourseDaoImpl  extends BaseDaoImpl<QualityCourse, Long> implements QualityCourseDao
{

    @Override
    public List<QualityCourse> findBySchool(DictSchool dictSchool, Long status, List<Order> orders)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QualityCourse> criteriaQuery = criteriaBuilder.createQuery(QualityCourse.class);
        Root<QualityCourse> root = criteriaQuery.from(QualityCourse.class);
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
        return super.findList(criteriaQuery, null, null, null, orders);
    }
}
