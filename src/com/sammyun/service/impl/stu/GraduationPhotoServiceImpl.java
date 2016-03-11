package com.sammyun.service.impl.stu;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.stu.GraduationPhotoDao;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.stu.GraduationPhoto;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.stu.GraduationPhotoService;

/**
 * ServiceImpl - 毕业合影
 * 


 */
@Service("graduationPhotoServiceImpl")
public class GraduationPhotoServiceImpl extends BaseServiceImpl<GraduationPhoto, Long> implements GraduationPhotoService
{
    @Resource(name = "graduationPhotoDaoImpl")
    private GraduationPhotoDao graduationPhotoDao;

    @Resource(name = "graduationPhotoDaoImpl")
    public void setBaseDao(GraduationPhotoDao graduationPhotoDao)
    {
        super.setBaseDao(graduationPhotoDao);
    }

    @Override
    public void deleteByDictClass(DictClass dictClass)
    {
        graduationPhotoDao.deleteByDictClass(dictClass);
    }
}
