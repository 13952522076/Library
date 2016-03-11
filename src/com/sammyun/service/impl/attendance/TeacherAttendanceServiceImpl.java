package com.sammyun.service.impl.attendance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.MemberDao;
import com.sammyun.dao.attendance.TeacherAttendanceDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.attendance.TeacherAskLeave;
import com.sammyun.entity.attendance.TeacherAttendance;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.attendance.TeacherAttendanceService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * ServiceImpl - 教师考勤
 * 


 */
@Service("teacherAttendanceServiceImpl")
public class TeacherAttendanceServiceImpl extends BaseServiceImpl<TeacherAttendance, Long> implements
        TeacherAttendanceService
{
    @Resource(name = "teacherAttendanceDaoImpl")
    private TeacherAttendanceDao teacherAttendanceDao;
    
    @Resource(name = "teacherAttendanceDaoImpl")
    public void setBaseDao(TeacherAttendanceDao teacherAttendanceDao){
        super.setBaseDao(teacherAttendanceDao);
    }
    
    @Resource(name = "memberDaoImpl")
    private MemberDao memberDao;
    

    @Override
    public List<TeacherAttendance> findTeacherAttendance(Member member,Date beginDate, Date endDate)
    {
        return teacherAttendanceDao.findTeacherAttendance(member, beginDate, endDate);
    }

    @Override
    public List<TeacherAttendance> findBySchool(DictSchool dictSchool)
    {
        if(dictSchool==null){
            return null;
        }
        List<Member> members = memberDao.findBySchoolAndType(dictSchool, MemberType.teacher);
        if(members==null||members.size()==0){
            return null;
        }
        List<TeacherAttendance> teacherAttendances = new ArrayList<TeacherAttendance>();
        for(Member member:members){
            List<TeacherAttendance> teacherAttendancesItem = teacherAttendanceDao.findByMember(member);
            teacherAttendances.addAll(teacherAttendancesItem);
        }
        return teacherAttendances;
    }

    @Override
    public Page<TeacherAttendance> findList(Member member, Date beginDate, Date endDate, Pageable pageable)
    {
        return teacherAttendanceDao.findList(member, beginDate, endDate, pageable);
    }
    
    @Override
    public Long findAttendanceCount(Member member,int year,int month)
    {
    	return teacherAttendanceDao.findAttendanceCount(member,year,month);
    }
    
    @Override
    public List<Date> findAttendanceDate(Member member,int year,int month)
    {
    	return teacherAttendanceDao.findAttendanceDate(member,year,month);
    }
    
    @Override
    public List<TeacherAskLeave> findAskLeave(Member member,Date minDate,Date maxDate)
    {
    	return teacherAttendanceDao.findAskLeave(member,minDate,maxDate);
    }
    
    @Override
    public Long findTardinessCount(Member member,TeacherAttendance.Status workStatus,int year,int month)
    {
    	return teacherAttendanceDao.findTardinessCount(member,workStatus,year,month);
    }
}
