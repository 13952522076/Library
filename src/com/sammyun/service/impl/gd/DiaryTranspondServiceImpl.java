package com.sammyun.service.impl.gd;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.gd.DiaryTranspondDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.gd.DiaryTranspond;
import com.sammyun.entity.gd.GrowthDiary;
import com.sammyun.service.gd.DiaryTranspondService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * DiaryTranspond * ServiceImpl - 
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("diaryTranspondServiceImpl")
public class DiaryTranspondServiceImpl extends BaseServiceImpl<DiaryTranspond, Long> implements DiaryTranspondService
{
    
    @Resource(name = "diaryTranspondDaoImpl")
    private DiaryTranspondDao diaryTranspondDao;

    @Resource(name = "diaryTranspondDaoImpl")
    public void setBaseDao(DiaryTranspondDao diaryTranspondDao)
    {
        super.setBaseDao(diaryTranspondDao);
    }

    @Override
    public Boolean isTransponded(Member member, GrowthDiary growthDiary)
    {
        // TODO Auto-generated method stub
        return diaryTranspondDao.isTransponded(member, growthDiary);
    }

}
