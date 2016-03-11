package com.sammyun.service.impl.campusviewImg;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.campusviewImg.CampusviewImageAttachDao;
import com.sammyun.entity.campusviewImg.CampusviewImageAttach;
import com.sammyun.entity.campusviewImg.CampusviewImg;
import com.sammyun.service.campusviewImg.CampusviewImageAttachService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * CampusviewImageAttachService * ServiceImpl - 校园相册图片附件
 * 


 */

@Service("campusviewImageAttachServiceImpl")
public class CampusviewImageAttachServiceImpl extends BaseServiceImpl<CampusviewImageAttach, Long> implements CampusviewImageAttachService
{
    @Resource(name = "campusviewImageAttachDaoImpl")
    private CampusviewImageAttachDao campusviewImageAttachDao;

    @Resource(name = "campusviewImageAttachDaoImpl")
    public void setBaseDao(CampusviewImageAttachDao campusviewImageAttachDao){
        super.setBaseDao(campusviewImageAttachDao);
    }

    /**
     * 删除 校园相册图片附件
     * 
     * @param campusviewImg 校园风光
     * @see [类、类#方法、类#成员]
     */
    @Override
    public void deleteByCampusviewImg(CampusviewImg campusviewImg)
    {
        campusviewImageAttachDao.deleteByCampusviewImg(campusviewImg);
    }
}
