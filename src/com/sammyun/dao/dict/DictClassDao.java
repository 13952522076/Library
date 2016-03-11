package com.sammyun.dao.dict;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictStudent;

/**
 * DictClass * Dao - 班级
 * 


 */
public interface DictClassDao extends BaseDao<DictClass, Long> {
    
	List<DictClass> getClassesBySchool(DictSchool dictschool);
	
	/**
     * 判断编号是否存在
     * 
     * @param code 编号
     * @return 编号是否存在
     */
    boolean codeExists(String code,DictSchool dictSchool);
    
    /**
     * 根据班级名查询出当前学校下唯一的相关班级
     * @param name
     * @param dictSchool
     * @return
     */
    List<DictClass> getClassByName(String name,DictSchool dictSchool);
    
    /**
     * 判断班级名称是否存在
     * 
     * @param className 班级名称
     * @return 名称是否存在
     */
    boolean classNameExist(String className,DictSchool dictSchool);
}
