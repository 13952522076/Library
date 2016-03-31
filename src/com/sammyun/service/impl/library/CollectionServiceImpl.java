package com.sammyun.service.impl.library;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.library.CollectionDao;
import com.sammyun.entity.Admin;
import com.sammyun.entity.library.Book;
import com.sammyun.entity.library.Collection;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.library.CollectionService;

/**
 * ServiceImpl - 喜欢收藏
 */
@Service("collectionServiceImpl")
public class CollectionServiceImpl extends BaseServiceImpl<Collection, Long> implements CollectionService
{
    @Resource(name = "collectionDaoImpl")
    private CollectionDao collectionDao;

    @Resource(name = "collectionDaoImpl")
    public void setBaseDao(CollectionDao collectionDao)
    {
        super.setBaseDao(collectionDao);
    }

    @Override
    public List<Collection> findByBookAndAdmin(Book book, Admin admin)
    {
        // TODO Auto-generated method stub
        return collectionDao.findByBookAndAdmin(book, admin);
    }

    @Override
    public List<Book> findMostCollection()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
