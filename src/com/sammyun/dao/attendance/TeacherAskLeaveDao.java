package com.sammyun.dao.attendance;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.BaseDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.TeacherAskLeave;

/**
 * Dao - 教师请假
 * 


 */
public interface TeacherAskLeaveDao extends BaseDao<TeacherAskLeave, Long>
{
    /**
     * 根据member查看请假记录
     * <功能详细描述>
     * @param member
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Page<TeacherAskLeave> findByMember(Member leaveMember,Member approvalMember,Pageable pageable);
}
