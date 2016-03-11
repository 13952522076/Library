package com.sammyun.dao.gd;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.gd.DiaryPicture;
import com.sammyun.entity.gd.GrowthDiary;

/**
 * DiaryPicture * Dao - 成长记图片附件
 * 


 */
public interface DiaryPictureDao extends BaseDao<DiaryPicture, Long>
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
