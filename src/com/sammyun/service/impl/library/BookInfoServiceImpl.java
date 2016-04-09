package com.sammyun.service.impl.library;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.library.BookInfoDao;
import com.sammyun.entity.library.Book;
import com.sammyun.entity.library.BookInfo;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.library.BookInfoService;

/**
 * ServiceImpl - 书籍
 */
@Service("bookInfoServiceImpl")
public class BookInfoServiceImpl extends BaseServiceImpl<BookInfo, Long> implements BookInfoService
{
    @Resource(name = "bookInfoDaoImpl")
    private BookInfoDao bookInfoDao;

    @Resource(name = "bookInfoDaoImpl")
    public void setBaseDao(BookInfoDao bookInfoDao)
    {
        super.setBaseDao(bookInfoDao);
    }

    @Override
    public BookInfo findByBook(Book book)
    {
        // TODO Auto-generated method stub
        return bookInfoDao.findByBook(book);
    }

}
