package com.sammyun.dao.impl.classalbum;

import javax.persistence.FlushModeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sammyun.dao.attendance.WorkSchedulingDao;
import com.sammyun.dao.classalbum.ClassAlbumImageAttachDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.classalbum.ClassAlbumImage;
import com.sammyun.entity.classalbum.ClassAlbumImageAttach;

/**
 * ClassAlbumImage * DaoImpl - 校园风光表
 * 
 * @author Sencloud Team
 * @version 3.0
 */

@Repository("classAlbumImageAttachDaoImpl")
public class ClassAlbumImageAttachDaoImpl extends BaseDaoImpl<ClassAlbumImageAttach, Long> implements ClassAlbumImageAttachDao
{
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(WorkSchedulingDao.class);

    @Override
    public void deleteByClassAlbumImage(ClassAlbumImage classAlbumImage)
    {
        String jpql = "delete from ClassAlbumImageAttach classAlbumImageAttach where classAlbumImageAttach.classAlbumImage = :classAlbumImage";
        entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("classAlbumImage", classAlbumImage).executeUpdate();
        
    }

}
