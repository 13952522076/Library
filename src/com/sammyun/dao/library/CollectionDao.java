package com.sammyun.dao.library;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.Admin;
import com.sammyun.entity.library.Book;
import com.sammyun.entity.library.Collection;

/**
 * Dao - 喜欢收藏
 */
public interface CollectionDao extends BaseDao<Collection, Long>
{
    /** 根据书和人查找到是否收藏 */
    List<Collection> findByBookAndAdmin(Book book, Admin admin);
}
