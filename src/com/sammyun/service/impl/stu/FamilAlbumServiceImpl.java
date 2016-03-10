package com.sammyun.service.impl.stu;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sammyun.dao.stu.FamilAlbumDao;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.stu.FamilAlbum;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.stu.FamilAlbumService;

/**
 * ServiceImpl - 学生作品
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("familAlbumServiceImpl")
public class FamilAlbumServiceImpl extends BaseServiceImpl<FamilAlbum, Long> implements FamilAlbumService
{
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(FamilAlbumServiceImpl.class);

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
