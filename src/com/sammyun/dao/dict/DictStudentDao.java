package com.sammyun.dao.dict;

import java.util.List;
import java.util.Set;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictStudent;;

/**
 * DictStudent * Dao - 学生
 * 


 */
public interface DictStudentDao extends BaseDao<DictStudent, Long> {
    
    /**
     * 判断学号是否唯一
     * 
     * @param studentNo
     * @return
     */
    boolean studentNoUnique(String studentNo,DictSchool dictSchool);
    
    /**根据班级查询到所有学生*/
    public List<DictStudent> getStudentByClass(DictClass dictclass);
    
    /**
    * 根据学号查学生
    * @param studentNo
    * @return
    * @see [类、类#方法、类#成员]
    */
   public List<DictStudent> findByStudentNo(String studentNo,DictSchool dictSchool);
   
   /**
    * 根据学校查询学生
    * <功能详细描述>
    * @param dictSchool
    * @return
    * @see [类、类#方法、类#成员]
    */
   List<DictStudent> findStudentsBySchool(DictSchool dictSchool);
   
   /**
    * 判断学号是否存在
    * 
    * @param stuName 用户名(忽略大小写)
    * @return 用户名是否存在
    */
   boolean studentNoExists(String studentNo,Set<DictClass> dictClasses);
   
   /**
    * 根据学号查学生
    * @param studentNo
    * @return
    * @see [类、类#方法、类#成员]
    */
   public DictStudent findByStudentNoSingle(String studentNo,DictSchool dictSchool);
    
}
