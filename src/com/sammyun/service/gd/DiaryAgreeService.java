package com.sammyun.service.gd;

import java.util.List;

import com.sammyun.entity.Member;
import com.sammyun.entity.gd.DiaryAgree;
import com.sammyun.entity.gd.GrowthDiary;
import com.sammyun.service.BaseService;

/**
 * DiaryAgree * Service - 成长记点赞
 * 
 * @author Sencloud Team

 */
public interface DiaryAgreeService extends BaseService<DiaryAgree, Long>
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
    
    /**
     * 获取当前人是否为此条纪录点赞
     * <功能详细描述>
     * @param member
     * @param members
     * @return
     * @see [类、类#方法、类#成员]
     */
    Boolean findIsagree(Member member,GrowthDiary growthDiary);
}
