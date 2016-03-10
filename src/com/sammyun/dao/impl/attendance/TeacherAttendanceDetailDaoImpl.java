package com.sammyun.dao.impl.attendance;

import java.util.Date;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.attendance.TeacherAttendanceDetailDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.attendance.TeacherAttendance;
import com.sammyun.entity.attendance.TeacherAttendanceDetail;

/**
 * DaoImpl - 教师考情详情
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("teacherAttendanceDetailDaoImpl")
public class TeacherAttendanceDetailDaoImpl extends BaseDaoImpl<TeacherAttendanceDetail, Long> implements
        TeacherAttendanceDetailDao
{

    @Override
    public List<TeacherAttendanceDetail> findByTeacherAttendance(TeacherAttendance teacherAttendance)
    {
        if (teacherAttendance == null)
        {
            return null;
        }
        String jpql = "select teacherAttendanceDetail from TeacherAttendanceDetail teacherAttendanceDetail where teacherAttendanceDetail.teacherAttendance = :teacherAttendance";
        return entityManager.createQuery(jpql, TeacherAttendanceDetail.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "teacherAttendance", teacherAttendance).getResultList();
    }

    @Override
    public List<TeacherAttendanceDetail> findByAttendance(TeacherAttendance teacherAttendance, Date clockInDate)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TeacherAttendanceDetail> criteriaQuery = criteriaBuilder.createQuery(TeacherAttendanceDetail.class);
        Root<TeacherAttendanceDetail> root = criteriaQuery.from(TeacherAttendanceDetail.class);
        criteriaQuery.select(root);

        Predicate restrictions = criteriaBuilder.conjunction();
        if (teacherAttendance != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("teacherAttendance"), teacherAttendance));
        }
        if (clockInDate != null)
        {
                restrictions = criteriaBuilder.and(restrictions,
                        criteriaBuilder.equal(root.<Date> get("clockInDate"), clockInDate));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

}
