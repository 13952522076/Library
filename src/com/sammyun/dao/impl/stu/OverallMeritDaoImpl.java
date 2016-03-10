package com.sammyun.dao.impl.stu;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.stu.OverallMeritDao;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.stu.OverallMerit;

/**
 * OverallMerit * DaoImpl - 综合评价
 * 
 * @author Sencloud Team

 */
@Repository("overallMeritDaoImpl")
public class OverallMeritDaoImpl extends BaseDaoImpl<OverallMerit, Long> implements OverallMeritDao
{

    @Override
    public Page<OverallMerit> findBySchool(DictSchool dictSchool,Pageable pageable)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OverallMerit> criteriaQuery = criteriaBuilder.createQuery(OverallMerit.class);
        Root<OverallMerit> root = criteriaQuery.from(OverallMerit.class);
        criteriaQuery.select(root);

        Predicate restrictions = criteriaBuilder.conjunction();
        if (dictSchool != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("meritTemplate").get("dictSchool"), dictSchool));
        }
        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery, pageable);
    }

    @Override
    public List<OverallMerit> findByDictStudent(DictStudent dictStudent)
    {
        if(dictStudent==null){
            return null;
        }
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OverallMerit> criteriaQuery = criteriaBuilder.createQuery(OverallMerit.class);
        Root<OverallMerit> root = criteriaQuery.from(OverallMerit.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        restrictions = criteriaBuilder.and(restrictions,
                criteriaBuilder.equal(root.get("dictStudent"),dictStudent));
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();   
    }

    @Override
    public void deleteByDictStudent(DictStudent dictStudent)
    {
        String jpql = "delete from OverallMerit overallMerit where overallMerit.dictStudent = :dictStudent";
        entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("dictStudent", dictStudent).executeUpdate();
        
    }

}
