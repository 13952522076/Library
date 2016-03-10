package com.sammyun.service.impl.gd;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.gd.DiaryAgreeDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.gd.DiaryAgree;
import com.sammyun.entity.gd.GrowthDiary;
import com.sammyun.service.gd.DiaryAgreeService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * DiaryAgree * ServiceImpl -
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("diaryAgreeServiceImpl")
public class DiaryAgreeServiceImpl extends BaseServiceImpl<DiaryAgree, Long> implements DiaryAgreeService
{

    @Resource(name = "diaryAgreeDaoImpl")
    private DiaryAgreeDao diaryAgreeDao;

    @Resource(name = "diaryAgreeDaoImpl")
    public void setBaseDao(DiaryAgreeDao diaryAgreeDao)
    {
        super.setBaseDao(diaryAgreeDao);
    }

    @Override
    public List<Member> findMemberByGrowthDiary(GrowthDiary growthDiary)
    {
        return diaryAgreeDao.findMemberByGrowthDiary(growthDiary);
    }

    @Override
    public List<DiaryAgree> findByMemberAndDiary(Member member, GrowthDiary growthDiary)
    {
        return diaryAgreeDao.findByMemberAndDiary(member, growthDiary);
    }

    @Override
    public Boolean findIsagree(Member member, GrowthDiary growthDiary)
    {
        // 这里是获取当前人是否为此条纪录点赞
        List<Member> agreeMembers = this.findMemberByGrowthDiary(growthDiary);
        if (agreeMembers == null || agreeMembers.size() == 0)
        {
            return false;
        }
        if (agreeMembers.contains(member))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
