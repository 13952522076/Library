package com.sammyun.dao.gd;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.gd.DiaryAgree;
import com.sammyun.entity.gd.GrowthDiary;

/**
 * DiaryAgree * Dao - 成长记点赞
 * 
 * @author Sencloud Team

 */
public interface DiaryAgreeDao extends BaseDao<DiaryAgree, Long>
{
    /**
     * 根据成长记获取其点赞人员列表
     * 
     * @param growthDiary
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<Member> findMemberByGrowthDiary(GrowthDiary growthDiary);
    
    /**
     * 根据用户，成长记查询出点赞
     * 
     * @param member
     * @param growthDiary
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<DiaryAgree> findByMemberAndDiary(Member member,GrowthDiary growthDiary);
}
