package com.sammyun.dao.dict;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.dict.DictSchool;

/**
 * DictSchool * Dao - 学校
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface DictSchoolDao extends BaseDao<DictSchool, Long> {

    /**
     * 判断编号是否存在
     * 
     * @param code 编号
     * @return 编号是否存在
     */
    boolean codeExists(String code);
}
