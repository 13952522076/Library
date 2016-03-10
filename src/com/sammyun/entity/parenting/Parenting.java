package com.sammyun.entity.parenting;

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
 * Entity - 育儿数据数据
 * 
 * @author xutianlong
 * @version [版本号, Apr 21, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_parenting")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_parenting_sequence")
public class Parenting extends BaseEntity
{
    private static final long serialVersionUID = 472157244756608110L;

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
    
    /** 是否置顶 */
    private Boolean isTop;

    /** 隶属的学校 */
    private DictSchool dictSchool;
    
    /** 育儿类别 */
    private ParentingCategory parentingCategory;

   /**
    * @return title
    */
    @NotNull
    @Column(name = "title", length = 500)
    public String getTitle()
    {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @return summary
     */
    public String getSummary()
    {
        return summary;
    }
    /**
     * @param summary
     */
    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    /**
     * @return content
     */
    public String getContent()
    {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content)
    {
        this.content = content;
    }

    /**
     * @return author
     */
    public String getAuthor()
    {
        return author;
    }

    /**
     * @param author
     */
    public void setAuthor(String author)
    {
        this.author = author;
    }

    /**
     * @return source
     */
    public String getSource()
    {
        return source;
    }

    /**
     * @param source
     */
    public void setSource(String source)
    {
        this.source = source;
    }

    /**
     * @return detailUrl
     */
    public String getDetailUrl()
    {
        return detailUrl;
    }

    /**
     * @param detailUrl
     */
    public void setDetailUrl(String detailUrl)
    {
        this.detailUrl = detailUrl;
    }

    /**
     * @return smallIconfile
     */
    public String getSmallIconfile()
    {
        return smallIconfile;
    }

    /**
     * @param smallIconfile
     */
    public void setSmallIconfile(String smallIconfile)
    {
        this.smallIconfile = smallIconfile;
    }

    /**
     * @return status
     */
    public Integer getStatus()
    {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    /**
     * @return timePublish
     */
    public Date getTimePublish()
    {
        return timePublish;
    }

    /**
     * @param timePublish
     */
    public void setTimePublish(Date timePublish)
    {
        this.timePublish = timePublish;
    }

    /**
     * @return viewCount
     */
    @Column(name = "view_count", scale = 0)
    public BigDecimal getViewCount()
    {
        return viewCount;
    }

    /**
     * @param viewCount
     */
    public void setViewCount(BigDecimal viewCount)
    {
        this.viewCount = viewCount;
    }

    /**
     * @return isTop
     */
    public Boolean getIsTop()
    {
        return isTop;
    }

    /**
     * @param isTop
     */
    public void setIsTop(Boolean isTop)
    {
        this.isTop = isTop;
    }

    /**
     * @return dictSchool
     */
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    public DictSchool getDictSchool()
    {
        return dictSchool;
    }

    /**
     * @param dictSchool
     */
    public void setDictSchool(DictSchool dictSchool)
    {
        this.dictSchool = dictSchool;
    }

    /**
     * @return parentingCategory
     */
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public ParentingCategory getParentingCategory()
    {
        return parentingCategory;
    }

    /**
     * @param parentingCategory
     */
    public void setParentingCategory(ParentingCategory parentingCategory)
    {
        this.parentingCategory = parentingCategory;
    }
    
}
