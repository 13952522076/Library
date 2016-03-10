package com.sammyun.dao.impl.gd;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.gd.DiaryPictureDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.Member;
import com.sammyun.entity.gd.DiaryPicture;
import com.sammyun.entity.gd.GrowthDiary;

/**
 * DiaryPicture * DaoImpl - 成长记图片附件
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("diaryPictureDaoImpl")
public class DiaryPictureDaoImpl extends BaseDaoImpl<DiaryPicture, Long> implements DiaryPictureDao
{

    @Override
    public List<String> findByGrowthDiary(GrowthDiary growthDiary)
    {
        // TODO Auto-generated method stub
        if (growthDiary == null)
        {
            return null;
        }
        String jpql = "select diaryPicture.pictureUrl from DiaryPicture diaryPicture where diaryPicture.growthDiary = :growthDiary";
        List<String> images = new ArrayList<String>();
        try
        {
            TypedQuery<String> flushModel = entityManager.createQuery(jpql, String.class).setFlushMode(
                    FlushModeType.COMMIT);
            flushModel.setParameter("growthDiary", growthDiary);
            images = (List<String>) flushModel.getResultList();
            return images;
        }
        catch (Exception e)
        {
            e.getMessage();
            return null;
        }
    }

}
