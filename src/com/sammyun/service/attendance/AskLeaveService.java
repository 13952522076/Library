package com.sammyun.service.attendance;

import java.util.List;

import com.sammyun.entity.attendance.AskLeave;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.service.BaseService;

/**
 * Service - 请假
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface AskLeaveService extends BaseService<AskLeave, Long>
{
    /**
     * 通过学生数组查询结果 <功能详细描述>
     * 
     * @param students
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<AskLeave> findByStudents(List<DictStudent> students);
}
