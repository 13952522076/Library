package com.sammyun.dao.impl.gd;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.gd.DiaryCollectionDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.gd.DiaryCollection;

/**
 * DiaryCollection * DaoImpl - 成长记收藏
 * 
 * @author Sencloud Team

 */
@Repository("diaryCollectionDaoImpl")
public class DiaryCollectionDaoImpl extends BaseDaoImpl<DiaryCollection, Long> implements DiaryCollectionDao
{

}
