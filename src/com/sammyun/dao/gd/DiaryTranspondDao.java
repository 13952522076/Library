package com.sammyun.dao.gd;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.gd.DiaryTranspond;
import com.sammyun.entity.gd.GrowthDiary;

/**
 * DiaryTranspond * Dao - 成长记转发
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface DiaryTranspondDao extends BaseDao<DiaryTranspond, Long>
{
    /**
     * 判断用户是否转发过此日记
     * 
     * @param member
     * @param growthDiary
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Boolean isTransponded(Member member, GrowthDiary growthDiary);
}
