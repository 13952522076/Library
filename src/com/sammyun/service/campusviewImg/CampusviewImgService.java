package com.sammyun.service.campusviewImg;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.entity.campusviewImg.CampusviewImg;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.BaseService;

/**
 * CampusviewImg * Service - 校园风光表
 * 
 * @author Sencloud Team

 */

public interface CampusviewImgService extends BaseService<CampusviewImg, Long> {

    /**
     * 根据学校查询校园相册
     */
    public Page<CampusviewImg> findBySchool(DictSchool dictSchool,Long status,Pageable pageable);
}
