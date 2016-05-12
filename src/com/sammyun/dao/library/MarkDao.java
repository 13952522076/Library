package com.sammyun.dao.library;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.Admin;
import com.sammyun.entity.library.Book;
import com.sammyun.entity.library.Mark;

/**
 * Dao - 评分
 */
public interface MarkDao extends BaseDao<Mark, Long>
{
    /** 通过书籍查找评论列表 */
    List<Mark> findListByBook(Book book);
    
    /** 通过用户查找到评论 */
    List<Mark> findListByAdmin(Admin admin);
}
