package com.sammyun.dao.impl.library;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.library.BookInfoDao;
import com.sammyun.entity.library.BookInfo;

/**
 * DaoImpl - 书籍统计信息
 */
@Repository("bookInfoDaoImpl")
public class BookInfoDaoImpl extends BaseDaoImpl<BookInfo, Long> implements BookInfoDao
{

}
