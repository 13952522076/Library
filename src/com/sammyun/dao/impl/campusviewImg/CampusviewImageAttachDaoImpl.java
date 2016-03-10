package com.sammyun.dao.impl.campusviewImg;

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.campusviewImg.CampusviewImageAttachDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.campusviewImg.CampusviewImageAttach;
import com.sammyun.entity.campusviewImg.CampusviewImg;

/**
 * CampusviewImg * DaoImpl - 校园风光表
 * 
 * @author Sencloud Team
 * @version 3.0
 */

@Repository("campusviewImageAttachDaoImpl")
public class CampusviewImageAttachDaoImpl extends BaseDaoImpl<CampusviewImageAttach, Long> implements
        CampusviewImageAttachDao
{

    /**
     * 删除 校园相册图片附件
     * 
     * @param campusviewImg 校园风光
     * @see [类、类#方法、类#成员]
     */
    @Override
    public void deleteByCampusviewImg(CampusviewImg campusviewImg)
    {
        String jpql = "delete from CampusviewImageAttach campusviewImageAttach where campusviewImageAttach.campusviewImg = :campusviewImg";
        entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("campusviewImg", campusviewImg).executeUpdate();
    }

}
