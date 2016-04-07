package com.sammyun.dao.impl.dict;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sammyun.dao.dict.PatriarchStudentMapDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.impl.recipe.RecipeDaoImpl;
import com.sammyun.entity.Member;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.dict.PatriarchStudentMap;

/**
 * PatriarchStudentMap * DaoImpl - 学生家长对应列表
 */
@Repository("patriarchStudentMapDaoImpl")
public class PatriarchStudentMapDaoImpl extends BaseDaoImpl<PatriarchStudentMap, Long> implements
        PatriarchStudentMapDao
{
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(RecipeDaoImpl.class);

    @Override
    public List<PatriarchStudentMap> findStudentByMember(Member member)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PatriarchStudentMap> criteriaQuery = criteriaBuilder.createQuery(PatriarchStudentMap.class);
        Root<PatriarchStudentMap> root = criteriaQuery.from(PatriarchStudentMap.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (member != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("member"), member));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

    @Override
    public PatriarchStudentMap findStudentByMember(Member member, DictStudent dictStudent)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PatriarchStudentMap> criteriaQuery = criteriaBuilder.createQuery(PatriarchStudentMap.class);
        Root<PatriarchStudentMap> root = criteriaQuery.from(PatriarchStudentMap.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (member != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("member"), member));
        }
        if (dictStudent != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("dictStudent"), dictStudent));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getSingleResult();
    }

    @Override
    public List<PatriarchStudentMap> findByStudent(DictStudent dictStudent)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PatriarchStudentMap> criteriaQuery = criteriaBuilder.createQuery(PatriarchStudentMap.class);
        Root<PatriarchStudentMap> root = criteriaQuery.from(PatriarchStudentMap.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (dictStudent != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("dictStudent"), dictStudent));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

    @Override
    public List<Member> findMemberByStudent(DictStudent dictStudent)
    {
        String jpql = "select patriarchStudentMap.member from PatriarchStudentMap patriarchStudentMap where 1=1 and patriarchStudentMap.dictStudent = :dictStudent";
        List<Member> members = null;
        try
        {
            TypedQuery<Member> flushModel = entityManager.createQuery(jpql, Member.class).setFlushMode(
                    FlushModeType.COMMIT);
            flushModel.setParameter("dictStudent", dictStudent);
            members = (List<Member>) flushModel.getResultList();
        }
        catch (Exception e)
        {
            logger.error("查询错误findMemberByStudent：" + e.getMessage());
        }

        return members;
    }

    @Override
    public List<DictStudent> findStudentByPatriarch(Member member)
    {
        String jpql = "select patriarchStudentMap.dictStudent from PatriarchStudentMap patriarchStudentMap where 1=1 and patriarchStudentMap.member = :member";
        List<DictStudent> dictStudents = null;
        try
        {
            TypedQuery<DictStudent> flushModel = entityManager.createQuery(jpql, DictStudent.class).setFlushMode(
                    FlushModeType.COMMIT);
            flushModel.setParameter("member", member);
            dictStudents = (List<DictStudent>) flushModel.getResultList();
        }
        catch (Exception e)
        {
            logger.error("查询错误findStudentByPatriarch：" + e.getMessage());
        }

        return dictStudents;
    }

}
