package com.sammyun.entity.announcement;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.dict.DictSchool;

/**
 * Entity - 通知公告数据
 */
@Entity
@Table(name = "t_pe_announcement")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_announcement_sequence")
public class Announcement extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 4897271934806222398L;

    /** 预览图 */
    private String smallIconfile;

    /** 标题 */
    private String title;

    /** 摘要 */
    private String summary;

    /** 正文 */
    private String content;

    /** 作者 */
    private String author;

    /** 来源 */
    private String source;

    /** 状态（0未屏蔽，1屏蔽） */
    private Integer status;

    /** 发布时间 */
    private Date timePublish;

    /** 浏览数 */
    private BigDecimal viewCount;

    /** 隶属的学校 */
    private DictSchool dictSchool;

    /**
     * @return 返回 smallIconfile
     */
    public String getSmallIconfile()
    {
        return smallIconfile;
    }

    /**
     * @param 对smallIconfile进行赋值
     */
    public void setSmallIconfile(String smallIconfile)
    {
        this.smallIconfile = smallIconfile;
    }

    /**
     * @return 返回 title
     */
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
     * @return 返回 summary
     */
    public String getSummary()
    {
        return summary;
    }

    /**
     * @param 对summary进行赋值
     */
    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    /**
     * @return 返回 content
     */
    public String getContent()
    {
        return content;
    }

    /**
     * @param 对content进行赋值
     */
    public void setContent(String content)
    {
        this.content = content;
    }

    /**
     * @return 返回 author
     */
    public String getAuthor()
    {
        return author;
    }

    /**
     * @param 对author进行赋值
     */
    public void setAuthor(String author)
    {
        this.author = author;
    }

    /**
     * @return 返回 source
     */
    public String getSource()
    {
        return source;
    }

    /**
     * @param 对source进行赋值
     */
    public void setSource(String source)
    {
        this.source = source;
    }

    /**
     * @return 返回 status
     */
    public Integer getStatus()
    {
        return status;
    }

    /**
     * @param 对status进行赋值
     */
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    /**
     * @return 返回 timePublish
     */
    public Date getTimePublish()
    {
        return timePublish;
    }

    /**
     * @param 对timePublish进行赋值
     */
    public void setTimePublish(Date timePublish)
    {
        this.timePublish = timePublish;
    }

    /**
     * @return 返回 viewCount
     */
    public BigDecimal getViewCount()
    {
        return viewCount;
    }

    /**
     * @param 对viewCount进行赋值
     */
    public void setViewCount(BigDecimal viewCount)
    {
        this.viewCount = viewCount;
    }

    /**
     * @return 返回 dictSchool
     */
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public DictSchool getDictSchool()
    {
        return dictSchool;
    }

    /**
     * @param 对dictSchool进行赋值
     */
    public void setDictSchool(DictSchool dictSchool)
    {
        this.dictSchool = dictSchool;
    }

}
