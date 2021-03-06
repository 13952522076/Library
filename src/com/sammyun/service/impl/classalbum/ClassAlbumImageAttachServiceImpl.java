package com.sammyun.service.impl.classalbum;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.classalbum.ClassAlbumImageAttachDao;
import com.sammyun.entity.classalbum.ClassAlbumImage;
import com.sammyun.entity.classalbum.ClassAlbumImageAttach;
import com.sammyun.service.classalbum.ClassAlbumImageAttachService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * ClassAlbumImage * ServiceImpl - 班级相册
 */

@Service("classAlbumImageAttachServiceImpl")
public class ClassAlbumImageAttachServiceImpl extends BaseServiceImpl<ClassAlbumImageAttach, Long> implements
        ClassAlbumImageAttachService
{
    @Resource(name = "classAlbumImageAttachDaoImpl")
    private ClassAlbumImageAttachDao classAlbumImageAttachDao;

    @Resource(name = "classAlbumImageAttachDaoImpl")
    public void setBaseDao(ClassAlbumImageAttachDao classAlbumImageAttachDao)
    {
        super.setBaseDao(classAlbumImageAttachDao);
    }

    @Override
    public void deleteByClassAlbumImage(ClassAlbumImage classAlbumImage)
    {
        classAlbumImageAttachDao.deleteByClassAlbumImage(classAlbumImage);

    }

}
