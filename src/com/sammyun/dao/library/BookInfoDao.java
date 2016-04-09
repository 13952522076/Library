package com.sammyun.dao.library;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.library.Book;
import com.sammyun.entity.library.BookInfo;

/**
 * Dao - 书籍统计信息
 */
public interface BookInfoDao extends BaseDao<BookInfo, Long>
{
    /**
     * 根据书找到书的统计信息 <功能详细描述>
     * 
     * @param book
     * @return
     * @see [类、类#方法、类#成员]
     */
    public BookInfo findByBook(Book book);
}
