package com.sammyun.entity.news;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
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
 * Entity - 新闻数据
 * 
 * @author xutianlong
 * @version [版本号, Apr 21, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_news")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_news_sequence")
public class News extends BaseEntity
{

    private static final long serialVersionUID = 4013435380529604993L;

    /** 标题 */
    private String title;

    /** 摘要 */
    private String summary;

    /** 正文 */
    private String content;

    /** 新闻作者 */
    private String author;

    /** 新闻来源 */
    private String source;

    /** 详情url地址 */
    private String detailUrl;

    /** 图标文件标识 */
    private String smallIconfile;

    /** 状态（0未屏蔽，1屏蔽） */
    private Integer status;

    /** 发布时间 */
    private Date timePublish;

    /** 浏览数 */
    private BigDecimal viewCount;

    /** 隶属的学校 */
    private DictSchool dictSchool;
    
    /** 新闻类别 */
    private NewsCategory newsCategory;
    

    @Column(name = "author", length = 100)
    public String getAuthor()
    {
        return this.author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    @Column(name = "content", length = 65535)
    public String getContent()
    {
        return this.content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    @Column(name = "detail_url", length = 500)
    public String getDetailUrl()
    {
        return this.detailUrl;
    }

    public void setDetailUrl(String detailUrl)
    {
        this.detailUrl = detailUrl;
    }

    @Column(name = "small_iconfile", length = 255)
    public String getSmallIconfile()
    {
        return this.smallIconfile;
    }

    public void setSmallIconfile(String smallIconfile)
    {
        this.smallIconfile = smallIconfile;
    }

    @Column(name = "source", length = 500)
    public String getSource()
    {
        return this.source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    @Column(name = "status", precision = 5, scale = 0)
    public Integer getStatus()
    {
        return this.status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    @Column(name = "summary", length = 1000)
    public String getSummary()
    {
        return this.summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    @Column(name = "time_publish", scale = 0)
    public Date getTimePublish()
    {
        return this.timePublish;
    }

    public void setTimePublish(Date timePublish)
    {
        this.timePublish = timePublish;
    }

    @NotNull
    @Column(name = "title", length = 500)
    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Column(name = "view_count", scale = 0)
    public BigDecimal getViewCount()
    {
        return this.viewCount;
    }

    public void setViewCount(BigDecimal viewCount)
    {
        this.viewCount = viewCount;
    }

    /**
     * @return 返回 newsCategory
     */
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public NewsCategory getNewsCategory()
    {
        return newsCategory;
    }

    /**
     * @param 对newsCategory进行赋值
     */
    public void setNewsCategory(NewsCategory newsCategory)
    {
        this.newsCategory = newsCategory;
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
