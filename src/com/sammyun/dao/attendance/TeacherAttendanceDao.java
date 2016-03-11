package com.sammyun.dao.attendance;

import java.util.Date;
import java.util.List;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.BaseDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.TeacherAskLeave;
import com.sammyun.entity.attendance.TeacherAttendance;

/**
 * Dao - 教师考勤
 * 


 */
public interface TeacherAttendanceDao extends BaseDao<TeacherAttendance, Long>
{
    /**
     * 根据教师、上班时间、下班时间查询考勤记录
     * <功能详细描述>
     * @param dictStudent
     * @param date
     * @return
     * @see [类、类#方法、类#成员]
     */
     public List<TeacherAttendance> findTeacherAttendance(Member member,Date beginDate, Date endDate);
     
     /**
      * 根据老师查询所有的考勤列表
      * <功能详细描述>
      * @param member
      * @return
      * @see [类、类#方法、类#成员]
      */
     public List<TeacherAttendance> findByMember(Member member);
     
     /**
      * 查找老师某时间段的考勤记录
      * 
      * @param member
      * @param beginDate
      * @param endDate
      * @param pageable
      * @return
      * @see [类、类#方法、类#成员]
      */
     public Page<TeacherAttendance> findList(Member member,Date beginDate,Date endDate,Pageable pageable);
     
     /**
      * 统计老师某月的出勤天数
      * 
      * @param member
      * @param year
      * @param month
      * @return
      * @see [类、类#方法、类#成员]
      */
     public Long findAttendanceCount(Member member,int year,int month);
     
     /**
      * 统计老师某月的出勤的刷卡日期
      * 
      * @param member
      * @param year
      * @param month
      * @return
      * @see [类、类#方法、类#成员]
      */
     public List<Date> findAttendanceDate(Member member,int year,int month);
     
     /**
      * 查找老师请假记录
      * 
      * @param member
      * @param minDate 该月最小日期
      * @param maxDate 该月最大日期
      * @return
      * @see [类、类#方法、类#成员]
      */
     public List<TeacherAskLeave> findAskLeave(Member member,Date minDate,Date maxDate);
     
     /**
      * 统计老师某月的迟到/早退
      * 
      * @param member
      * @param workStatus
      * @param year 年
      * @param month 月
      * @return
      * @see [类、类#方法、类#成员]
      */
     public Long findTardinessCount(Member member,TeacherAttendance.Status workStatus,int year,int month);
    
     /**
      * 查询跨月请假 请假开始时间在本月的date
      * 
      * @param member
      * @param year 年
      * @param month 月
      */
     public Date findLeaveDateStart(Member member,int year,int month);
}