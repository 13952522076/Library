package com.sammyun.service.impl.gd;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.gd.DiaryTagDao;
import com.sammyun.entity.gd.DiaryTag;
import com.sammyun.service.gd.DiaryTagService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * DiaryTag * ServiceImpl -成长记标签
 * 


 */
@Service("diaryTagServiceImpl")
public class DiaryTagServiceImpl extends BaseServiceImpl<DiaryTag, Long> implements DiaryTagService
{

    @Resource(name = "diaryTagDaoImpl")
    private DiaryTagDao diaryTagDao;

    @Resource(name = "diaryTagDaoImpl")
    public void setBaseDao(DiaryTagDao diaryTagDao)
    {
        super.setBaseDao(diaryTagDao);
    }

    @Override
    public List<DiaryTag> findByName(String name)
    {
        return diaryTagDao.findByName(name);
    }

}
