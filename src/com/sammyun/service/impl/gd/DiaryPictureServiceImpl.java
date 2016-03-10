package com.sammyun.service.impl.gd;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.gd.DiaryPictureDao;
import com.sammyun.entity.gd.DiaryPicture;
import com.sammyun.entity.gd.GrowthDiary;
import com.sammyun.service.gd.DiaryPictureService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * DiaryPicture * ServiceImpl -
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("diaryPictureServiceImpl")
public class DiaryPictureServiceImpl extends BaseServiceImpl<DiaryPicture, Long> implements DiaryPictureService
{

    @Resource(name = "diaryPictureDaoImpl")
    private DiaryPictureDao diaryPictureDao;

    @Resource(name = "diaryPictureDaoImpl")
    public void setBaseDao(DiaryPictureDao diaryPictureDao)
    {
        super.setBaseDao(diaryPictureDao);
    }

    @Override
    public List<String> findByGrowthDiary(GrowthDiary growthDiary)
    {
        return diaryPictureDao.findByGrowthDiary(growthDiary);
    }

}
