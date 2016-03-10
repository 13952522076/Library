package com.sammyun.dao.stu;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.stu.HealthFile;

public interface HealthFileDao extends BaseDao<HealthFile, Long>
{
    /**
     * 根据学生来查询其健康档案
     * 
     * @param dictStudent
     * @return
     * @see [类、类#方法、类#成员]
     */
    public HealthFile findByDictStudent(DictStudent dictStudent);

}
