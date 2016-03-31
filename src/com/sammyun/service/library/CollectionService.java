package com.sammyun.service.library;

import java.util.List;

import com.sammyun.entity.Admin;
import com.sammyun.entity.library.Book;
import com.sammyun.entity.library.Collection;
import com.sammyun.service.BaseService;

/**
 * Service - 喜欢收藏
 */
public interface CollectionService extends BaseService<Collection, Long>
{
   /**根据书和人查找到是否收藏 */
   List<Collection> findByBookAndAdmin(Book book,Admin admin);
   
}
