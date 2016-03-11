package com.sammyun.dao.impl.attendance;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.attendance.TeacherAskLeaveDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.TeacherAskLeave;

/**
 * DaoImpl - 教师请假
 * 


 */
@Repository("teacherAskLeaveDaoImpl")
public class TeacherAskLeaveDaoImpl extends BaseDaoImpl<TeacherAskLeave, Long> implements TeacherAskLeaveDao
{

    @Override
    public Page<TeacherAskLeave> findByMember(Member leaveMember, Member approvalMember,Pageable pageable)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TeacherAskLeave> criteriaQuery = criteriaBuilder.createQuery(TeacherAskLeave.class);
        Root<TeacherAskLeave> root = criteriaQuery.from(TeacherAskLeave.class);
        criteriaQuery.select(root);

        Predicate restrictions = criteriaBuilder.conjunction();
        if (leaveMember != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("leaveMember"), leaveMember));
        }
        if (approvalMember != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("approvalMember"), approvalMember));
        }

        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery, pageable);
    }

}
