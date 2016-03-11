/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service.impl.attendance;

import java.util.Date;
import java.util.List;

import com.sammyun.dao.attendance.AttendanceDetailDao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.service.attendance.AttendanceDetailService;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.entity.attendance.Attendance;
import com.sammyun.entity.attendance.AttendanceDetail;


/**
 * ServiceImpl - 考勤
 * 


 */
@Service("attendanceDetailServiceImpl")
public class AttendanceDetailServiceImpl extends BaseServiceImpl<AttendanceDetail, Long> implements AttendanceDetailService
{
	@Resource(name = "attendanceDetailDaoImpl")
    private AttendanceDetailDao attendanceDetailDao;
	
    @Resource(name = "attendanceDetailDaoImpl")
    public void setBaseDao(AttendanceDetailDao attendanceDetailDao){
        super.setBaseDao(attendanceDetailDao);
    }

    @Override
    public List<AttendanceDetail> getDetailsByAttendance(Attendance attendance)
    {
        return attendanceDetailDao.getDetailByAttendance(attendance);
    }

    @Override
    public List<AttendanceDetail> findByAttendance(Attendance attendance, Date clockInDate)
    {
        return attendanceDetailDao.findByAttendance(attendance, clockInDate);
    }

}
