package com.sammyun.dao.impl.library;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.library.CollectionDao;
import com.sammyun.entity.library.Collection;

/**
 *  DaoImpl - 喜欢收藏
 */
@Repository("collectionDaoImpl")
public class CollectionDaoImpl extends BaseDaoImpl<Collection, Long> implements CollectionDao  {

   

}
