package com.sammyun.service.impl.gd;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.gd.DiaryCommentDao;
import com.sammyun.entity.gd.DiaryComment;
import com.sammyun.service.gd.DiaryCommentService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * DiaryComment * ServiceImpl - 
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("diaryCommentServiceImpl")
public class DiaryCommentServiceImpl extends BaseServiceImpl<DiaryComment, Long> implements DiaryCommentService
{
    
    @Resource(name = "diaryCommentDaoImpl")
    private DiaryCommentDao diaryCommentDao;

    @Resource(name = "diaryCommentDaoImpl")
    public void setBaseDao(DiaryCommentDao diaryCommentDao)
    {
        super.setBaseDao(diaryCommentDao);
    }

}
