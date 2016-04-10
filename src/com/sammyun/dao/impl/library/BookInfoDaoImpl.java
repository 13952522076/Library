package com.sammyun.dao.impl.library;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.library.BookInfoDao;
import com.sammyun.entity.library.Book;
import com.sammyun.entity.library.BookInfo;

/**
 * DaoImpl - 书籍统计信息
 */
@Repository("bookInfoDaoImpl")
public class BookInfoDaoImpl extends BaseDaoImpl<BookInfo, Long> implements BookInfoDao
{

    @Override
    public BookInfo findByBook(Book book)
    {
        if (book == null)
        {
            return null;
        }
        String jpql = "select bookInfo from BookInfo bookInfo where bookInfo.book = :book ";
        try
        {
            return entityManager.createQuery(jpql, BookInfo.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                    "book", book).getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

}
