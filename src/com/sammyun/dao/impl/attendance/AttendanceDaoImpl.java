/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao.impl.attendance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.attendance.AttendanceDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.Attendance;
import com.sammyun.entity.attendance.Attendance.Status;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictStudent;

/**
 * DaoImpl - 考勤
 * 
 * @author Sencloud Team

 */
@Repository("attendanceDaoImpl")
public class AttendanceDaoImpl extends BaseDaoImpl<Attendance, Long> implements AttendanceDao
{

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(AttendanceDaoImpl.class);

    /**
     * 查询考勤列表
     * 
     * @param status 考勤状态
     * @param dictStudents 学生
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 考勤列表
     */
    @Override
    public List<Attendance> getAttendanceByConditions(Status status, List<DictStudent> dictStudents, String startDate,
            String endDate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = null;
        Date endTime = null;
        try
        {
            if (startDate != null)
            {
                startTime = sdf.parse(startDate);
            }
            if (endDate != null)
            {
                endTime = sdf.parse(endDate);
            }
        }
        catch (ParseException e)
        {
            logger.error("字符串转换日期失败");
        }

        String jpql = "select attendance from Attendance attendance where 1=1 ";
        if (status != null)
        {
            jpql = jpql + "and attendance.status = :status ";
        }
        if (dictStudents != null && dictStudents.size() > 0)
        {
            jpql = jpql + "and attendance.dictStudent in :dictStudents ";
        }
        else
        {
            return null;
        }
        if (startTime != null)
        {
            jpql = jpql + "and attendance.attendanceDate >= :startTime ";
        }
        if (endTime != null)
        {
            jpql = jpql + "and attendance.attendanceDate <= :endTime ";
        }

        TypedQuery<Attendance> flushModel = entityManager.createQuery(jpql, Attendance.class).setFlushMode(
                FlushModeType.COMMIT);

        if (status != null)
        {
            flushModel.setParameter("status", status);
        }
        if (dictStudents != null && dictStudents.size() > 0)
        {
            flushModel.setParameter("dictStudents", dictStudents);
        }
        else
        {
            return null;
        }
        if (startTime != null)
        {
            flushModel.setParameter("startTime", startTime);
        }
        if (endTime != null)
        {
            flushModel.setParameter("endTime", endTime);
        }
        List<Attendance> attendances = (List<Attendance>) flushModel.getResultList();
        return attendances;
    }

    @Override
    public Page<Attendance> findAttendance(Member member, DictStudent dictStudent, DictClass dictClass, Date beginDate,
            Date endDate, Pageable pageable)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Attendance> criteriaQuery = criteriaBuilder.createQuery(Attendance.class);
        Root<Attendance> root = criteriaQuery.from(Attendance.class);
        Join<Attendance, DictStudent> dictStudents = root.join("dictStudent");
        criteriaQuery.select(root);

        Predicate restrictions = criteriaBuilder.conjunction();
        if (beginDate != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.greaterThanOrEqualTo(root.<Date> get("attendanceDate"), beginDate));
        }
        if (endDate != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.lessThanOrEqualTo(root.<Date> get("attendanceDate"), endDate));
        }
        if (dictStudent != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("dictStudent"), dictStudent));
        }
        if (dictClass != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(dictStudents.get("dictClass"), dictClass));
        }

        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery, pageable);
    }

    @Override
    public List<Attendance> findAttendance(DictStudent dictStudent, Date date)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Attendance> criteriaQuery = criteriaBuilder.createQuery(Attendance.class);
        Root<Attendance> root = criteriaQuery.from(Attendance.class);
        criteriaQuery.select(root);

        Predicate restrictions = criteriaBuilder.conjunction();
        if (date != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.<Date> get("attendanceDate"), date));
        }
        if (dictStudent != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("dictStudent"), dictStudent));
        }

        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }
}
