package com.sammyun.dao.impl.attendance;


import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sammyun.dao.attendance.WorkSchedulingDao;
import com.sammyun.dao.impl.BaseDaoImpl;


import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.WorkScheduling;
import com.sammyun.entity.recipe.Recipe;

/**
 * DaoImpl - 排班管理
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("workSchedulingDaoImpl")
public class WorkSchedulingDaoImpl extends BaseDaoImpl<WorkScheduling, Long> implements WorkSchedulingDao
{
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(WorkSchedulingDao.class);
    
    @Override
    public List<WorkScheduling> findWorkScheduling(Member member)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<WorkScheduling> criteriaQuery = criteriaBuilder.createQuery(WorkScheduling.class);
        Root<WorkScheduling> root = criteriaQuery.from(WorkScheduling.class);
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
    public List<Member> findMembers()
    {
        String jpql = "select workScheduling.member from WorkScheduling workScheduling";
        List<Member> members = new ArrayList<Member>();
        try
        {
            TypedQuery<Member> flushModel = entityManager.createQuery(jpql, Member.class).setFlushMode(
                    FlushModeType.COMMIT);
            members = (List<Member>) flushModel.getResultList();
        }
        catch (Exception e)
        {
            logger.error("WorkSchedulingDaoImpl.findMembers通过排班dao查询：" + e.getMessage());
        }

        return members;
    }

}
