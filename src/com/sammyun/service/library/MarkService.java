package com.sammyun.service.library;

import java.util.List;

import com.sammyun.entity.Admin;
import com.sammyun.entity.library.Book;
import com.sammyun.entity.library.Mark;
import com.sammyun.form.KeyValue;
import com.sammyun.service.BaseService;

/**
 * Service - 评分
 */
public interface MarkService extends BaseService<Mark, Long>
{
    /** 查找出评论最多的书 */
    List<KeyValue> findMostMark(List<Book> books);

    /** 通过书籍查找评论列表 */
    List<Mark> findListByBook(Book book);
    
    /** 查找评论分数最高的书 */
    List<KeyValue> findTopMark(List<Book> books);
    
    /** 通过用户查找到评论 */
    List<Mark> findListByAdmin(Admin admin);
    
    
    
    
}
