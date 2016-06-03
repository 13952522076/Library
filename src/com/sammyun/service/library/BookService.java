package com.sammyun.service.library;

import java.util.List;

import com.sammyun.entity.library.Book;
import com.sammyun.service.BaseService;

/**
 * Service - 书籍
 */
public interface BookService extends BaseService<Book, Long>
{
    /** 根据关键字找书 */
    public List<Book> findByKeyword(String keyword);
}
