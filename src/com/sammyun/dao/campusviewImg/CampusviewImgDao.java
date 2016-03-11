package com.sammyun.dao.campusviewImg;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.BaseDao;
import com.sammyun.entity.campusviewImg.CampusviewImg;
import com.sammyun.entity.dict.DictSchool;

/**
 * CampusviewImg * Dao - 校园风光表
 * 


 */

public interface CampusviewImgDao extends BaseDao<CampusviewImg, Long> {
    
    /**
     * 根据学校查询校园相册
     */
    public Page<CampusviewImg> findBySchool(DictSchool dictSchool,Long status,Pageable pageable);

}
