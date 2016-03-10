package com.sammyun.dao.impl.system;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.system.SystemDictDao;
import com.sammyun.entity.system.SystemDict;

/**
 * Dao - 系统字典
 * 
 * @author Sencloud Team

 */
@Repository("systemDictDaoImpl")
public class SystemDictDaoImpl extends BaseDaoImpl<SystemDict, Long> implements SystemDictDao
{

    @Override
    public List<SystemDict> findByCode(String code)
    {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SystemDict> criteriaQuery = criteriaBuilder.createQuery(SystemDict.class);
        Root<SystemDict> root = criteriaQuery.from(SystemDict.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (code != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("code"), code));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

    @Override
    public List<SystemDict> findByCodeDesc(String codeDesc)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SystemDict> criteriaQuery = criteriaBuilder.createQuery(SystemDict.class);
        Root<SystemDict> root = criteriaQuery.from(SystemDict.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (codeDesc != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("codeDesc"), codeDesc));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

}
