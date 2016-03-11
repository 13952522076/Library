package com.sammyun.service.attendance;

import java.util.List;

import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.WorkScheduling;
import com.sammyun.service.BaseService;

/**
 * Service - 排班管理
 * 


 */
public interface WorkSchedulingService  extends BaseService<WorkScheduling, Long>
{
    /**
     * 根据教师查询其排班管理
     * <功能详细描述>
     * @param dictStudent
     * @param date
     * @return
     * @see [类、类#方法、类#成员]
     */
     public List<WorkScheduling> findWorkScheduling(Member member);
     /**
     * 查找到所有存在的老师
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Member> findMembers();
}
