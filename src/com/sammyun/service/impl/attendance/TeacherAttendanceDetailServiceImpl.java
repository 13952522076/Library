package com.sammyun.service.impl.attendance;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.attendance.TeacherAttendanceDetailDao;
import com.sammyun.entity.attendance.TeacherAttendance;
import com.sammyun.entity.attendance.TeacherAttendanceDetail;
import com.sammyun.service.attendance.TeacherAttendanceDetailService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * ServiceImpl - 教师考情详情
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("teacherAttendanceDetailServiceImpl")
public class TeacherAttendanceDetailServiceImpl extends BaseServiceImpl<TeacherAttendanceDetail, Long> implements
        TeacherAttendanceDetailService
{
    @Resource(name = "teacherAttendanceDetailDaoImpl")
    private TeacherAttendanceDetailDao teacherAttendanceDetailDao;
    
    @Resource(name = "teacherAttendanceDetailDaoImpl")
    public void setBaseDao(TeacherAttendanceDetailDao teacherAttendanceDetailDao){
        super.setBaseDao(teacherAttendanceDetailDao);
    }

    @Override
    public List<TeacherAttendanceDetail> findByTeacherAttendance(TeacherAttendance teacherAttendance)
    {
        // TODO Auto-generated method stub
        return teacherAttendanceDetailDao.findByTeacherAttendance(teacherAttendance);
    }

    @Override
    public List<TeacherAttendanceDetail> findByAttendance(TeacherAttendance teacherAttendance, Date clockInDate)
    {
        // TODO Auto-generated method stub
        return teacherAttendanceDetailDao.findByAttendance(teacherAttendance, clockInDate);
    }
}
