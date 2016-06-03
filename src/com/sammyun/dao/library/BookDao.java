package com.sammyun.dao.library;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.library.Book;

/**
 * Dao - 书籍
 */
public interface BookDao extends BaseDao<Book, Long>
{

    /** 根据关键字找书 */
    public List<Book> findByKeyword(String keyword);
}
