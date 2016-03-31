package com.sammyun.entity.library;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.Admin;
import com.sammyun.entity.BaseEntity;

/**
 * 图书收藏
 * 
 * @author maxu
 */
@Entity
@Table(name = "t_pe_collection")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_collection_sequence")
public class Collection extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -2798028155336348301L;

    /** 收藏的书 */
    private Book book;

    /** 收藏的人 */
    private Admin admin;

    @ManyToOne(fetch = FetchType.LAZY)
    public Book getBook()
    {
        return book;
    }

    public void setBook(Book book)
    {
        this.book = book;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Admin getAdmin()
    {
        return admin;
    }

    public void setAdmin(Admin admin)
    {
        this.admin = admin;
    }

}
