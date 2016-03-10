package com.sammyun.dao.impl.gd;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.gd.GrowthDiaryDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.Member;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.gd.DiaryTag;
import com.sammyun.entity.gd.GrowthDiary;
import com.sammyun.entity.parenting.Parenting;

/**
 * GrowthDiary * DaoImpl - 成长记
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("growthDiaryDaoImpl")
public class GrowthDiaryDaoImpl extends BaseDaoImpl<GrowthDiary, Long> implements GrowthDiaryDao
{

    @Override
    public Page<GrowthDiary> findPage(List<Member> friends, Pageable pageable)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GrowthDiary> criteriaQuery = criteriaBuilder.createQuery(GrowthDiary.class);
        Root<GrowthDiary> root = criteriaQuery.from(GrowthDiary.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (friends != null)
        {
            restrictions = criteriaBuilder.and(restrictions, root.get("member").in(friends));
        }
        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery, pageable);
    }

    @Override
    public List<GrowthDiary> findByMember(Member member)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GrowthDiary> criteriaQuery = criteriaBuilder.createQuery(GrowthDiary.class);
        Root<GrowthDiary> root = criteriaQuery.from(GrowthDiary.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (member != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("member"), member));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

    @Override
    public List<GrowthDiary> findByDiaryTag(DiaryTag diaryTag)
    {
        if(diaryTag==null){
            return null;
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GrowthDiary> criteriaQuery = criteriaBuilder.createQuery(GrowthDiary.class);
        Root<GrowthDiary> root = criteriaQuery.from(GrowthDiary.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.join("diaryTags"), diaryTag));
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

}
