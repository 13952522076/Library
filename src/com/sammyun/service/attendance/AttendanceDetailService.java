/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service.attendance;

import java.util.Date;
import java.util.List;

import com.sammyun.service.BaseService;
import com.sammyun.entity.attendance.Attendance;
import com.sammyun.entity.attendance.AttendanceDetail;

/**
 * Service - 考勤详情
 * 
 * @author Sencloud Team

 */
public interface AttendanceDetailService extends BaseService<AttendanceDetail, Long>
{
   /**根据考勤纪录查询所有的考勤详情*/
    public List<AttendanceDetail> getDetailsByAttendance(Attendance attendance);
    
    /**
     * 根据考勤纪录查询和时间查询所有的考勤详情
     * @param attendance
     * @return
     */
    public List<AttendanceDetail> findByAttendance(Attendance attendance,Date clockInDate );
}
