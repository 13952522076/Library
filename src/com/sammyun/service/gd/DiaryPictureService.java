package com.sammyun.service.gd;

import java.util.List;

import com.sammyun.entity.gd.DiaryPicture;
import com.sammyun.entity.gd.GrowthDiary;
import com.sammyun.service.BaseService;

/**
 * DiaryPicture * Service - 成长记图片附件
 * 
 * @author Sencloud Team

 */
public interface DiaryPictureService extends BaseService<DiaryPicture, Long>
{
    /**
     * 根据成长记查找出对应的图片地址列表
     * 
     * @param growthDiary
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<String> findByGrowthDiary(GrowthDiary growthDiary);
}
