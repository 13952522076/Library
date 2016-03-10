package com.sammyun.service.impl.attendance;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.attendance.TeacherAskLeaveDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.TeacherAskLeave;
import com.sammyun.service.attendance.TeacherAskLeaveService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * ServiceImpl - 教师请假
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("teacherAskLeaveServiceImpl")
public class TeacherAskLeaveServiceImpl extends BaseServiceImpl<TeacherAskLeave, Long> implements TeacherAskLeaveService
{
    @Resource(name = "teacherAskLeaveDaoImpl")
    private TeacherAskLeaveDao teacherAskLeaveDao;
    
    @Resource(name = "teacherAskLeaveDaoImpl")
    public void setBaseDao(TeacherAskLeaveDao teacherAskLeaveDao){
        super.setBaseDao(teacherAskLeaveDao);
    }

    @Override
    public Page<TeacherAskLeave> findByMember(Member leaveMember,Member approvalMember,Pageable pageable)
    {
        return teacherAskLeaveDao.findByMember(leaveMember,approvalMember,pageable);
    }
}
