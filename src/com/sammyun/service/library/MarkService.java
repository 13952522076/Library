package com.sammyun.service.library;

import java.util.List;
import java.util.Map;

import com.sammyun.entity.library.Book;
import com.sammyun.entity.library.Mark;
import com.sammyun.service.BaseService;

/**
 * Service - 评分
 */
public interface MarkService extends BaseService<Mark, Long>
{
    /** 查找出评论最多的书 */
    Map<Integer,Book> findMostMark(List<Book> books);
    
    /** 通过书籍查找评论列表 */
    List<Mark> findListByBook(Book book);
}
