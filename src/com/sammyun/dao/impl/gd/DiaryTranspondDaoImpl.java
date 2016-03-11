package com.sammyun.dao.impl.gd;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.gd.DiaryTranspondDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.Member;
import com.sammyun.entity.gd.DiaryTranspond;
import com.sammyun.entity.gd.GrowthDiary;

/**
 * DiaryTranspond * DaoImpl - 成长记转发
 * 


 */
@Repository("diaryTranspondDaoImpl")
public class DiaryTranspondDaoImpl extends BaseDaoImpl<DiaryTranspond, Long> implements DiaryTranspondDao
{

    @Override
    public Boolean isTransponded(Member member, GrowthDiary growthDiary)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DiaryTranspond> criteriaQuery = criteriaBuilder.createQuery(DiaryTranspond.class);
        Root<DiaryTranspond> root = criteriaQuery.from(DiaryTranspond.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (member != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("member"), member));
        }
        if (growthDiary != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("growthDiary"), growthDiary));
        }
        criteriaQuery.where(restrictions);
        List<DiaryTranspond> diaryTransponds = entityManager.createQuery(criteriaQuery).setFlushMode(
                FlushModeType.COMMIT).getResultList();
        if (diaryTransponds == null || diaryTransponds.size() == 0)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

}
