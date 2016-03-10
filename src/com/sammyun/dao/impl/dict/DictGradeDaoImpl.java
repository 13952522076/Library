package com.sammyun.dao.impl.dict;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.dict.DictGradeDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.dict.DictGrade;
import com.sammyun.entity.dict.DictSchool;

/**
 * DaoImpl - 年级
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("dictGradeDaoImpl")
public class DictGradeDaoImpl extends BaseDaoImpl<DictGrade, Long> implements DictGradeDao
{

    @Override
    public List<DictGrade> findByName(String name, DictSchool dictSchool)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DictGrade> criteriaQuery = criteriaBuilder.createQuery(DictGrade.class);
        Root<DictGrade> root = criteriaQuery.from(DictGrade.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (name != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("gradeName"), name));
        }
        if (dictSchool != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("dictSchool"), dictSchool));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

}
