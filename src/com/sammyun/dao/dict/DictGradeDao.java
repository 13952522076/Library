package com.sammyun.dao.dict;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.dict.DictGrade;
import com.sammyun.entity.dict.DictSchool;

/**
 *  Dao - 年级
 * 
 * @author Sencloud Team

 */
public interface DictGradeDao extends BaseDao<DictGrade, Long> 
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
