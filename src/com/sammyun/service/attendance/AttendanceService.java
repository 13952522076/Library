/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service.attendance;

import java.util.Date;
import java.util.List;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.Attendance;
import com.sammyun.entity.attendance.Attendance.Status;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.service.BaseService;

/**
 * Service - 考勤
 * 
 * @author Sencloud Team

 */
public interface AttendanceService extends BaseService<Attendance, Long>
{

    /**
     * 查询考勤列表
     * 
     * @param status 考勤状态
     * @param dictStudents 学生
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 考勤列表
     */
    public List<Attendance> getAttendanceByConditions(Status status, List<DictStudent> dictStudents, String startDate,
            String endDate);
    
    /**
     * 根据学生，老师，班级查询考勤记录
     * @param member
     * @param dictStudent
     * @param dictClass
     * @param beginDate
     * @param endDate
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Page<Attendance> findAttendance(Member member, DictStudent dictStudent, DictClass dictClass, Date beginDate,
            Date endDate,Pageable pageable);
    
   /**
    * 根据学生和时间查询考勤记录
    * @param dictStudent
    * @param date
    * @return
    * @see [类、类#方法、类#成员]
    */
    public List<Attendance> findAttendance(DictStudent dictStudent, Date date);
}
