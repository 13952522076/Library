package com.sammyun.service.stu;

import java.util.List;

import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.stu.StudentWorks;
import com.sammyun.service.BaseService;

/**
 * Service - 学生作品
 * 
 * @author Sencloud Team

 */
public interface StudentWorksService extends BaseService<StudentWorks, Long>
{
    /**
     * 根据学生查找出其所有作品
     * 
     * @param dictStudent
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<StudentWorks> getListByDictStudent(DictStudent dictStudent);
    
    /**
     * 根据学生删除其对应的所有作品
     * @param dictStudent
     * @see [类、类#方法、类#成员]
     */
    public void deleteByDictStudent(DictStudent dictStudent);
}
