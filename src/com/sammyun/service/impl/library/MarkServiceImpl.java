package com.sammyun.service.impl.library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.library.MarkDao;
import com.sammyun.entity.library.Book;
import com.sammyun.entity.library.Mark;
import com.sammyun.form.KeyValue;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.library.MarkService;

/**
 * ServiceImpl - 评分
 */
@Service("markServiceImpl")
public class MarkServiceImpl extends BaseServiceImpl<Mark, Long> implements MarkService
{
    @Resource(name = "markDaoImpl")
    private MarkDao markDao;

    @Resource(name = "markDaoImpl")
    public void setBaseDao(MarkDao markDao)
    {
        super.setBaseDao(markDao);
    }

    @Override
    public List<KeyValue> findMostMark(List<Book> books)
    {
        List<KeyValue> bookNums = new ArrayList<KeyValue>();
        for (Book book : books)
        {
            Set<Mark> marks = book.getMarks();
            KeyValue bookNum = new KeyValue(book, marks.size());
            bookNums.add(bookNum);
        }
        // KeyValue对象已经实现Comparable接口
        Collections.sort(bookNums);
        // 取前5条数据
        if (bookNums.size() > 5)
        {
            bookNums = bookNums.subList(0, 5);

        }
        return bookNums;
    }

    @Override
    public List<Mark> findListByBook(Book book)
    {
        return markDao.findListByBook(book);
    }

    @Override
    public List<KeyValue> findTopMark(List<Book> books)
    {
        // TODO Auto-generated method stub
        
        return null;
    }

}
