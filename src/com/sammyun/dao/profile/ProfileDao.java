package com.sammyun.dao.profile;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.profile.Profile;

/**
 * Profiles * Dao - 学校概况数据
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface ProfileDao extends BaseDao<Profile, Long> {
    
    /**
     * 根据学校查询校介绍信息
     */
   public Profile findBySchool(DictSchool dictSchool);

}
