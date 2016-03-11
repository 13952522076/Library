/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service.impl.attendance;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.attendance.AttendanceDao;
import com.sammyun.dao.dict.DictStudentDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.Attendance;
import com.sammyun.entity.attendance.Attendance.Status;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.service.attendance.AttendanceService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * ServiceImpl - 考勤
 * 


 */
@Service("attendanceServiceImpl")
public class AttendanceServiceImpl extends BaseServiceImpl<Attendance, Long> implements AttendanceService
{
    @Resource(name = "attendanceDaoImpl")
    private AttendanceDao attendanceDao;

    @Resource(name = "dictStudentDaoImpl")
    private DictStudentDao dictStudentDao;

    @Resource(name = "attendanceDaoImpl")
    public void setBaseDao(AttendanceDao attendanceDao)
    {
        super.setBaseDao(attendanceDao);
    }

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
        List<Attendance> attendances = attendanceDao.getAttendanceByConditions(status, dictStudents, startDate, endDate);
        return attendances;
    }

    @Override
    public Page<Attendance> findAttendance(Member member, DictStudent dictStudent, DictClass dictClass, Date beginDate,
            Date endDate,Pageable pageable)
    {
        return attendanceDao.findAttendance(member, dictStudent, dictClass, beginDate,endDate,pageable);
    }

    @Override
    public List<Attendance> findAttendance(DictStudent dictStudent, Date date)
    {
        return attendanceDao.findAttendance(dictStudent, date);
    }
}
