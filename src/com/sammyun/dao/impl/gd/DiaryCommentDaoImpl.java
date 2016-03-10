package com.sammyun.dao.impl.gd;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.gd.DiaryCommentDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.gd.DiaryComment;

/**
 * DiaryComment * DaoImpl - 成长记评论
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("diaryCommentDaoImpl")
public class DiaryCommentDaoImpl extends BaseDaoImpl<DiaryComment, Long> implements DiaryCommentDao
{

}
