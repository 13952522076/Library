package com.sammyun.service.campusviewImg;

import com.sammyun.entity.campusviewImg.CampusviewImageAttach;
import com.sammyun.entity.campusviewImg.CampusviewImg;
import com.sammyun.service.BaseService;

public interface CampusviewImageAttachService extends BaseService<CampusviewImageAttach, Long>
{

    /**
     * 删除 校园相册图片附件
     * 
     * @param campusviewImg 校园风光
     * @see [类、类#方法、类#成员]
     */
    void deleteByCampusviewImg(CampusviewImg campusviewImg);

}
