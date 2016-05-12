package com.sammyun.dao.impl.library;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.library.SimilarFormDao;
import com.sammyun.entity.library.SimilarForm;

/**
 * DaoImpl - 相似度矩阵
 */
@Repository("similarFormDaoImpl")
public class SimilarFormDaoImpl extends BaseDaoImpl<SimilarForm, Long> implements SimilarFormDao
{

    @Override
    public List<SimilarForm> getSimilarForms(Long adminId)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SimilarForm> criteriaQuery = criteriaBuilder.createQuery(SimilarForm.class);
        Root<SimilarForm> root = criteriaQuery.from(SimilarForm.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (adminId != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("adminId"), adminId));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

}
