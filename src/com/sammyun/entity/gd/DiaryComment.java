package com.sammyun.entity.gd;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.Member;

/**
 * 成長日記评论表
 * 
 * @author xutianlong
 * @version [版本号, Jul 3, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_diary_comment")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_diary_comment_sequence")
public class DiaryComment extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    /** 評論狀態 */
    public enum CommentInfostatu
    {
        /** 正常 */
        normal,
        /** 刪除 */
        delete

    }

    /** 评论内容 */
    private String commentsInfo;

    /** 内容状态 */
    private CommentInfostatu commentInfostatu;

    /** 评论时间 */
    private Date commentsTime;

    /** 隸屬成長日記 */
    private GrowthDiary growthDiary;

    /** 評論人 */
    private Member member;

    /**
     * @return 返回 commentsInfo
     */
    public String getCommentsInfo()
    {
        return commentsInfo;
    }

    /**
     * @param 对commentsInfo进行赋值
     */
    public void setCommentsInfo(String commentsInfo)
    {
        this.commentsInfo = commentsInfo;
    }

    /**
     * @return 返回 commentInfostatu
     */
    public CommentInfostatu getCommentInfostatu()
    {
        return commentInfostatu;
    }

    /**
     * @param 对commentInfostatu进行赋值
     */
    public void setCommentInfostatu(CommentInfostatu commentInfostatu)
    {
        this.commentInfostatu = commentInfostatu;
    }

    /**
     * @return 返回 commentsTime
     */
    public Date getCommentsTime()
    {
        return commentsTime;
    }

    /**
     * @param 对commentsTime进行赋值
     */
    public void setCommentsTime(Date commentsTime)
    {
        this.commentsTime = commentsTime;
    }

    /**
     * @return 返回 growthDiary
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public GrowthDiary getGrowthDiary()
    {
        return growthDiary;
    }

    /**
     * @param 对growthDiary进行赋值
     */
    public void setGrowthDiary(GrowthDiary growthDiary)
    {
        this.growthDiary = growthDiary;
    }

    /**
     * @return 返回 member
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Member getMember()
    {
        return member;
    }

    /**
     * @param 对member进行赋值
     */
    public void setMember(Member member)
    {
        this.member = member;
    }

}
