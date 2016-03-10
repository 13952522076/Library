package com.sammyun.entity.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.Member;

/**
 * Entity - 应用评论
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Entity
@Table(name = "t_pe_app_review")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_app_review_sequence")
public class AppReview extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 3527592666004266776L;

    /** 评分（最高位5颗星（1到5）） */
    private Integer score;

    /** 评论标题 */
    private String title;

    /** 昵称 */
    private String nickName;

    /** 内容 */
    private String content;

    /** 是否显示 */
    private Boolean isShow;

    /** IP */
    private String ip;

    /** 会员 */
    private Member member;

    /** 隶属评论 */
    private App app;

    /**
     * 获取评分
     * 
     * @return 评分
     */
    @NotNull
    @Min(1)
    @Max(5)
    @Column(nullable = false, updatable = false)
    public Integer getScore()
    {
        return score;
    }

    /**
     * 设置评分
     * 
     * @param score 评分
     */
    public void setScore(Integer score)
    {
        this.score = score;
    }

    /**
     * 获取内容
     * 
     * @return 内容
     */
    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false, updatable = false)
    public String getContent()
    {
        return content;
    }

    /**
     * 设置内容
     * 
     * @param content 内容
     */
    public void setContent(String content)
    {
        this.content = content;
    }

    /**
     * 获取是否显示
     * 
     * @return 是否显示
     */
    @Column(nullable = false)
    public Boolean getIsShow()
    {
        return isShow;
    }

    /**
     * 设置是否显示
     * 
     * @param isShow 是否显示
     */
    public void setIsShow(Boolean isShow)
    {
        this.isShow = isShow;
    }

    /**
     * 获取IP
     * 
     * @return IP
     */
    @Column(nullable = false, updatable = false)
    public String getIp()
    {
        return ip;
    }

    /**
     * 设置IP
     * 
     * @param ip IP
     */
    public void setIp(String ip)
    {
        this.ip = ip;
    }

    /**
     * 获取会员
     * 
     * @return 会员
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false)
    public Member getMember()
    {
        return member;
    }

    /**
     * 设置会员
     * 
     * @param member 会员
     */
    public void setMember(Member member)
    {
        this.member = member;
    }

    /**
     * 获取商品
     * 
     * @return 商品
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, updatable = false)
    public App getApp()
    {
        return app;
    }

    /**
     * 设置商品
     * 
     * @param product 商品
     */
    public void setApp(App app)
    {
        this.app = app;
    }

    /**
     * @return 返回 title
     */
    @NotEmpty
    @Length(max = 50)
    @Column(nullable = false, updatable = false)
    public String getTitle()
    {
        return title;
    }

    /**
     * @param 对title进行赋值
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @return 返回 nickName
     */
    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false, updatable = false)
    public String getNickName()
    {
        return nickName;
    }

    /**
     * @param 对nickName进行赋值
     */
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }
}
