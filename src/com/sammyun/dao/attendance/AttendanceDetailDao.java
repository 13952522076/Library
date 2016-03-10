/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao.attendance;

import java.util.Date;
import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.attendance.Attendance;
import com.sammyun.entity.attendance.AttendanceDetail;

/**
 * Dao - 考勤详情
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface AttendanceDetailDao extends BaseDao<AttendanceDetail, Long>
{
    /**根据考勤纪录查找考勤详情*/
    public List<AttendanceDetail> getDetailByAttendance(Attendance attendance);
    
    /**
     * 根据考勤纪录查询和时间查询所有的考勤详情
     * @param attendance
     * @return
     */
    public List<AttendanceDetail> findByAttendance(Attendance attendance,Date clockInDate );
}
