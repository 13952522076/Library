package com.sammyun.dao.impl.classalbum;

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.classalbum.ClassAlbumImageAttachDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.classalbum.ClassAlbumImage;
import com.sammyun.entity.classalbum.ClassAlbumImageAttach;

/**
 * ClassAlbumImage * DaoImpl - 校园风光表
 */

@Repository("classAlbumImageAttachDaoImpl")
public class ClassAlbumImageAttachDaoImpl extends BaseDaoImpl<ClassAlbumImageAttach, Long> implements
        ClassAlbumImageAttachDao
{

    @Override
    public void deleteByClassAlbumImage(ClassAlbumImage classAlbumImage)
    {
        String jpql = "delete from ClassAlbumImageAttach classAlbumImageAttach where classAlbumImageAttach.classAlbumImage = :classAlbumImage";
        entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("classAlbumImage",
                classAlbumImage).executeUpdate();

    }

}
