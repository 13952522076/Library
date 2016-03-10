package com.sammyun.dao.attendance;

import java.util.Date;
import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.attendance.TeacherAttendance;
import com.sammyun.entity.attendance.TeacherAttendanceDetail;

/**
 * Dao - 教师考情详情
 * 
 * @author Sencloud Team

 */
public interface TeacherAttendanceDetailDao extends BaseDao<TeacherAttendanceDetail, Long>
{
    /**
     * 通过老师考勤查找考勤详情
     * <功能详细描述>
     * @param teacherAttendacne
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<TeacherAttendanceDetail> findByTeacherAttendance(TeacherAttendance teacherAttendance);
    
    /**
     * 根据考勤纪录查询和时间查询所有的考勤详情
     * @param attendance
     * @return
     */
    public List<TeacherAttendanceDetail> findByAttendance(TeacherAttendance teacherAttendance,Date clockInDate );
}
