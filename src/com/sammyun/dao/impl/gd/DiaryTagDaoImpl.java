package com.sammyun.dao.impl.gd;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.gd.DiaryTagDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.gd.DiaryTag;

/**
 * DiaryTag * DaoImpl - 成长记点赞
 * 
 * @author Sencloud Team

 */
@Repository("diaryTagDaoImpl")
public class DiaryTagDaoImpl extends BaseDaoImpl<DiaryTag, Long> implements DiaryTagDao
{

    @Override
    public List<DiaryTag> findByName(String name)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DiaryTag> criteriaQuery = criteriaBuilder.createQuery(DiaryTag.class);
        Root<DiaryTag> root = criteriaQuery.from(DiaryTag.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (name != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("name"), name));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

}
