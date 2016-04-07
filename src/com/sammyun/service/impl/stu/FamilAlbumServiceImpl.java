package com.sammyun.service.impl.stu;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.stu.FamilAlbumDao;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.stu.FamilAlbum;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.stu.FamilAlbumService;

/**
 * ServiceImpl - 学生作品
 */
@Service("familAlbumServiceImpl")
public class FamilAlbumServiceImpl extends BaseServiceImpl<FamilAlbum, Long> implements FamilAlbumService
{

    @Resource(name = "familAlbumDaoImpl")
    private FamilAlbumDao familAlbumDao;

    @Resource(name = "familAlbumDaoImpl")
    public void setBaseDao(FamilAlbumDao familAlbumDao)
    {
        super.setBaseDao(familAlbumDao);
    }

    @Override
    public void deleteByDictStudent(DictStudent dictStudent)
    {
        // TODO Auto-generated method stub
        familAlbumDao.deleteByDictStudent(dictStudent);
    }

}
