package com.sammyun.dao.campusviewImg;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.campusviewImg.CampusviewImageAttach;
import com.sammyun.entity.campusviewImg.CampusviewImg;

/**
 * Dao - 校园相册图片附件
 * 


 */
public interface CampusviewImageAttachDao extends BaseDao<CampusviewImageAttach, Long>
{

    /**
     * 删除 校园相册图片附件
     * 
     * @param campusviewImg 校园风光
     * @see [类、类#方法、类#成员]
     */
    void deleteByCampusviewImg(CampusviewImg campusviewImg);

}
