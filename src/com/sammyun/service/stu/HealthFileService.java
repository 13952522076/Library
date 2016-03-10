package com.sammyun.service.stu;

import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.stu.HealthFile;
import com.sammyun.service.BaseService;

/**
 * Service - 健康档案
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface HealthFileService extends BaseService<HealthFile, Long>
{
    /**
     *根据学生来查询其健康档案
     * @param dictStudent
     * @return
     * @see [类、类#方法、类#成员]
     */
    public HealthFile findByDictStudent(DictStudent dictStudent);
}
