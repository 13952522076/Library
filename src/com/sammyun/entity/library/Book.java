package com.sammyun.entity.library;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.Admin;
import com.sammyun.entity.BaseEntity;

/**
 * 图书
 * 
 * @author maxu
 */
@Entity
@Table(name = "t_pe_book")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_book_sequence")
public class Book extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -2798028155336348301L;

    /**
     * 书名
     */
    private String name;

    /** 作者 */
    private String author;

    /** 出版日期 */
    private Date publishDate;

    /** 入库日期 */
    private Date putingDate;

    /** 价格 */
    private Double price;

    /** 出版社 */
    private String publishCompany;

    /** 简介 */
    private String description;

    /** 藏书数 */
    private int count;

    /** 封面 */
    private String cover;

    @JsonProperty
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @JsonProperty
    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    @JsonProperty
    public Date getPublishDate()
    {
        return publishDate;
    }

    public void setPublishDate(Date publishDate)
    {
        this.publishDate = publishDate;
    }

    @JsonProperty
    public Date getPutingDate()
    {
        return putingDate;
    }

    public void setPutingDate(Date putingDate)
    {
        this.putingDate = putingDate;
    }

    @JsonProperty
    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    @JsonProperty
    public String getPublishCompany()
    {
        return publishCompany;
    }

    public void setPublishCompany(String publishCompany)
    {
        this.publishCompany = publishCompany;
    }

    @JsonProperty
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @JsonProperty
    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    @JsonProperty
    public String getCover()
    {
        return cover;
    }

    public void setCover(String cover)
    {
        this.cover = cover;
    }
}
