package com.sammyun.dao.impl.stu;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.stu.MeritTemplateDao;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.stu.MeritTemplate;

/**
 * MeritTemplate * DaoImpl - 评价等级模板
 * 


 */
@Repository("meritTemplateDaoImpl")
public class MeritTemplateDaoImpl extends BaseDaoImpl<MeritTemplate, Long> implements MeritTemplateDao
{

    @Override
    public List<MeritTemplate> findBySchool(DictSchool dictSchool)
    {
        if(dictSchool==null){
            return null;
        }
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MeritTemplate> criteriaQuery = criteriaBuilder.createQuery(MeritTemplate.class);
        Root<MeritTemplate> root = criteriaQuery.from(MeritTemplate.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        restrictions = criteriaBuilder.and(restrictions,
                criteriaBuilder.equal(root.get("dictSchool"),dictSchool));
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();   
    }

}
