package com.sammyun.service.dict;

import java.util.List;

import com.sammyun.entity.dict.DictGrade;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.BaseService;

/**
 *  Service - 年级
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface DictGradeService  extends BaseService<DictGrade, Long> 
{
    /**
     * 根据年级名和学校查询处年级
     * 
     * @param name
     * @param dictSchool
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<DictGrade> findByName(String name, DictSchool dictSchool);
}
