package com.sammyun.service.impl.classalbum;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.classalbum.ClassAlbumImageDao;
import com.sammyun.entity.classalbum.ClassAlbumImage;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.classalbum.ClassAlbumImageService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * ClassAlbumImage * ServiceImpl - 班级相册
 * 
 * @author Sencloud Team
 * @version 3.0
 */

@Service("classAlbumImageServiceImpl")
public class ClassAlbumImageServiceImpl extends BaseServiceImpl<ClassAlbumImage, Long> implements ClassAlbumImageService
{
    @Resource(name = "classAlbumImageDaoImpl")
    private ClassAlbumImageDao classAlbumImageDao;

    @Resource(name = "classAlbumImageDaoImpl")
    public void setBaseDao(ClassAlbumImageDao classAlbumImageDao){
        super.setBaseDao(classAlbumImageDao);
    }

    @Override
    public Page<ClassAlbumImage> findBySchool(DictSchool dictSchool, DictClass dictClass, Long status, Pageable pageable)
    {
        return classAlbumImageDao.findBySchool(dictSchool, dictClass, status, pageable);
    }

    @Override
    public List<DictClass> getDictClasses(DictSchool dictSchool)
    {
        // TODO Auto-generated method stub
        return classAlbumImageDao.getDictClasses(dictSchool);
    }
}
