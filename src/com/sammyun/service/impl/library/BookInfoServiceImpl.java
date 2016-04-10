package com.sammyun.service.impl.library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.library.BookInfoDao;
import com.sammyun.entity.library.Book;
import com.sammyun.entity.library.BookInfo;
import com.sammyun.form.KeyValue;
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

    @Override
    public List<KeyValue> findTopMark(List<Book> books)
    {
        List<KeyValue> bookScores = new ArrayList<KeyValue>();
        for (Book book : books)
        {
            BookInfo bookInfo = book.getBookInfo();
            if (bookInfo == null)
            {
                continue;
            }
            KeyValue bookScore = new KeyValue(book, bookInfo.getScore());
            bookScores.add(bookScore);
        }
        // KeyValue对象已经实现Comparable接口
        Collections.sort(bookScores);
        // 取前5条数据
        if (bookScores.size() > 5)
        {
            bookScores = bookScores.subList(0, 5);

        }
        return bookScores;
    }

}
