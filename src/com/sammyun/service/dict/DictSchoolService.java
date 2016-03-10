package com.sammyun.service.dict;

import com.sammyun.service.BaseService;
import com.sammyun.entity.dict.DictSchool;

/**
 * DictSchool * Service - 学校
 * 
 * @author Sencloud Team

 */
public interface DictSchoolService extends BaseService<DictSchool, Long> {
    
    /**
     * 判断编号是否存在
     * 
     * @param code 编号
     * @return 编号是否存在
     */
    boolean codeExists(String code);
}
