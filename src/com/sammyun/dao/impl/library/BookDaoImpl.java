package com.sammyun.dao.impl.library;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.library.BookDao;
import com.sammyun.entity.library.Book;

/**
 *  DaoImpl - 书籍
 */
@Repository("bookDaoImpl")
public class BookDaoImpl extends BaseDaoImpl<Book, Long> implements BookDao  {

   

}
