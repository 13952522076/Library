package com.sammyun.service.attendance;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.TeacherAskLeave;
import com.sammyun.service.BaseService;

/**
 * Service - 教师请假
 * 


 */

public interface TeacherAskLeaveService extends BaseService<TeacherAskLeave, Long>
{
    /**
     * 根据member查看请假记录
     * <功能详细描述>
     * @param leaveMember
     * @param approvalMember
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Page<TeacherAskLeave> findByMember(Member leaveMember,Member approvalMember,Pageable pageable);
}
