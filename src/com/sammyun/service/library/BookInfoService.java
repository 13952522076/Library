package com.sammyun.service.library;

import java.util.List;

import com.sammyun.entity.library.Book;
import com.sammyun.entity.library.BookInfo;
import com.sammyun.form.KeyValue;
import com.sammyun.service.BaseService;

/**
 * Service - 书籍统计信息
 */
public interface BookInfoService extends BaseService<BookInfo, Long>
{
    /**
     * 根据书找到书的统计信息
     * <功能详细描述>
     * @param book
     * @return
     * @see [类、类#方法、类#成员]
     */
    public BookInfo findByBook(Book book);
    
    /** 查找出评论最高的书 */
    List<KeyValue> findTopMark(List<Book> books);
}
