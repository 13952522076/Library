/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao.impl.attendance;


import java.util.Date;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.attendance.AttendanceDetailDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.attendance.Attendance;
import com.sammyun.entity.attendance.AttendanceDetail;
import com.sammyun.entity.attendance.WorkScheduling;


/**
 * DaoImpl - 考勤详情
 * 
 * @author Sencloud Team

 */
@Repository("attendanceDetailDaoImpl")
public class AttendanceDetailDaoImpl extends BaseDaoImpl<AttendanceDetail, Long> implements AttendanceDetailDao
{

    @Override
    public List<AttendanceDetail> getDetailByAttendance(Attendance attendance)
    {
        // TODO Auto-generated method stub
        
        String jpql = "select attendanceDetail from AttendanceDetail attendanceDetail where 1=1 ";
        if(attendance != null){
            jpql = jpql +"and attendanceDetail.attendance = :attendance";
        }
        TypedQuery<AttendanceDetail> flushModel = entityManager.createQuery(jpql, AttendanceDetail.class).setFlushMode(FlushModeType.COMMIT);
        if(attendance!=null){
            flushModel.setParameter("attendance", attendance);
        }
        List<AttendanceDetail> attendances= (List<AttendanceDetail>)flushModel.getResultList();
        return attendances;
    }

    @Override
    public List<AttendanceDetail> findByAttendance(Attendance attendance, Date clockInDate)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AttendanceDetail> criteriaQuery = criteriaBuilder.createQuery(AttendanceDetail.class);
        Root<AttendanceDetail> root = criteriaQuery.from(AttendanceDetail.class);
        criteriaQuery.select(root);

        Predicate restrictions = criteriaBuilder.conjunction();
        if (attendance != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("attendance"), attendance));
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
