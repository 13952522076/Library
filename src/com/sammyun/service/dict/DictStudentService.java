package com.sammyun.service.dict;

import java.util.List;

import com.sammyun.service.BaseService;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictStudent;

/**
 * DictStudent * Service - 学生
 * 
 * @author Sencloud Team

 */
public interface DictStudentService extends BaseService<DictStudent, Long> {
    
    /**
     * 判断学号是否唯一
     * @param previousStudentNo
     * @param studentNo
     * @return
     * @see [类、类#方法、类#成员]
     */
    boolean studentNoUnique(String previousStudentNo, String studentNo,DictSchool dictSchool);
    
    /**
     * 根据班级查询出学生列表
     * <功能详细描述>
     * @param dictClassId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<DictStudent> getDictStudentByClassId(String dictClassId);
    
    /**
     * 根据学号查学生
     * @param studentNo
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<DictStudent> findByStudentNo(String studentNo,DictSchool dictSchool);
    
    /**
     * 根据学号查学生
     * @param studentNo
     * @return
     * @see [类、类#方法、类#成员]
     */
    public DictStudent findByStudentNoSingle(String studentNo,DictSchool dictSchool);
    
    /**
     * 根据学校查找出所有学生
     * <功能详细描述>
     * @param dictSchool
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<DictStudent> findStudentsBySchool(DictSchool dictSchool);
    
}
