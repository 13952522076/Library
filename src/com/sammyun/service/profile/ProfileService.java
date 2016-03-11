package com.sammyun.service.profile;

import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.profile.Profile;
import com.sammyun.service.BaseService;

/**
 * Profiles * Service - 学校概况数据
 * 


 */
public interface ProfileService extends BaseService<Profile, Long> {
    
    /**
     * 根据学校查询校介绍信息
     */
   public Profile findBySchool(DictSchool dictSchool);
}
