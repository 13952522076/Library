package com.sammyun.dao.impl.gd;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.gd.DiaryAgreeDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.Member;
import com.sammyun.entity.gd.DiaryAgree;
import com.sammyun.entity.gd.GrowthDiary;
import com.sammyun.entity.parenting.Parenting;

/**
 * DiaryAgree * DaoImpl - 成长记点赞
 * 
 * @author Sencloud Team

 */
@Repository("diaryAgreeDaoImpl")
public class DiaryAgreeDaoImpl extends BaseDaoImpl<DiaryAgree, Long> implements DiaryAgreeDao
{

    @Override
    public List<Member> findMemberByGrowthDiary(GrowthDiary growthDiary)
    {
        if (growthDiary == null)
        {
            return null;
        }
        String jpql = "select diaryAgree.member from DiaryAgree diaryAgree where diaryAgree.growthDiary = :growthDiary";
        List<Member> members = new ArrayList<Member>();
        try
        {
            TypedQuery<Member> flushModel = entityManager.createQuery(jpql, Member.class).setFlushMode(
                    FlushModeType.COMMIT);
            flushModel.setParameter("growthDiary", growthDiary);
            members = (List<Member>) flushModel.getResultList();
            return members;
        }
        catch (Exception e)
        {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<DiaryAgree> findByMemberAndDiary(Member member, GrowthDiary growthDiary)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DiaryAgree> criteriaQuery = criteriaBuilder.createQuery(DiaryAgree.class);
        Root<DiaryAgree> root = criteriaQuery.from(DiaryAgree.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (member != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("member"), member));
        }
        if (growthDiary != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("growthDiary"), growthDiary));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

}
