package com.sammyun.entity.library;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;

/**
 * 图书统计表
 * 
 * @author maxu
 */
@Entity
@Table(name = "t_pe_book_info")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_book_info_sequence")
public class BookInfo extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 6700357951376968523L;

    /** 对应书本 */
    private Book book;

    /** 此项目目前的普通平均值 */
    private Float rating;

    /** 此项目的最终评分 */
    private Float score;

    /** 此项目目前投票人数（可优化） */
    private Float voters;

    @OneToOne(fetch = FetchType.LAZY)
    public Book getBook()
    {
        return book;
    }

    public void setBook(Book book)
    {
        this.book = book;
    }

    public Float getRating()
    {
        return rating;
    }

    public void setRating(Float rating)
    {
        this.rating = rating;
    }

    public Float getVoters()
    {
        return voters;
    }

    public void setVoters(Float voters)
    {
        this.voters = voters;
    }

    public Float getScore()
    {
        return score;
    }

    public void setScore(Float score)
    {
        this.score = score;
    }

}
