package com.sammyun.service.dict;

import java.util.List;

import com.sammyun.service.BaseService;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;

/**
 * DictClass * Service - 班级
 * 
 * @author Sencloud Team

 */
public interface DictClassService extends BaseService<DictClass, Long>
{

    /**
     * 根据学校获得相关班级
     * 
     * @param school
     * @return List<DictClass>
     */
    List<DictClass> getClassesBySchool(DictSchool school);
    
    /**
     * 判断编号是否存在
     * 
     * @param code 编号
     * @return 编号是否存在
     */
    boolean codeExists(String code,DictSchool dictSchool); 
    
    /**
     * 判断班级名称是否存在
     * 
     * @param classnName 班级名称
     * @return 名称是否存在
     */
    boolean classNameExists(String className,DictSchool dictSchool);
}
