package com.sammyun.service.impl.classalbum;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.classalbum.ClassAlbumImageAttachDao;
import com.sammyun.dao.classalbum.ClassAlbumImageDao;
import com.sammyun.entity.classalbum.ClassAlbumImage;
import com.sammyun.entity.classalbum.ClassAlbumImageAttach;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.classalbum.ClassAlbumImageAttachService;
import com.sammyun.service.classalbum.ClassAlbumImageService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * ClassAlbumImage * ServiceImpl - 班级相册
 * 
 * @author Sencloud Team
 * @version 3.0
 */

@Service("classAlbumImageAttachServiceImpl")
public class ClassAlbumImageAttachServiceImpl extends BaseServiceImpl<ClassAlbumImageAttach, Long> implements ClassAlbumImageAttachService
{
    @Resource(name = "classAlbumImageAttachDaoImpl")
    private ClassAlbumImageAttachDao classAlbumImageAttachDao;

    @Resource(name = "classAlbumImageAttachDaoImpl")
    public void setBaseDao(ClassAlbumImageAttachDao classAlbumImageAttachDao){
        super.setBaseDao(classAlbumImageAttachDao);
    }

    @Override
    public void deleteByClassAlbumImage(ClassAlbumImage classAlbumImage)
    {
        classAlbumImageAttachDao.deleteByClassAlbumImage(classAlbumImage);
        
    }

}
