package com.sammyun.service.impl.library;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.library.BookDao;
import com.sammyun.entity.library.Book;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.library.BookService;

/**
 * ServiceImpl - 书籍
 */
@Service("bookServiceImpl")
public class BookServiceImpl extends BaseServiceImpl<Book, Long> implements BookService
{
    @Resource(name = "bookDaoImpl")
    private BookDao bookDao;

    @Resource(name = "bookDaoImpl")
    public void setBaseDao(BookDao bookDao){
        super.setBaseDao(bookDao);
    }


}
