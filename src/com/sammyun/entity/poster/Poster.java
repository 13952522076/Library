package com.sammyun.entity.poster;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.dict.DictSchool;

/**
 * Entity - 海报
 * 


 */
@Entity
@Table(name = "t_pe_poster")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_poster_sequence")
public class Poster extends BaseEntity
{

    /** 每个幼儿园的海报数量不能超过5个 */
    public static final Integer MAX_RECORD = 5;

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    /** 海报位置 **/
    public enum PosterPosition
    {
        /** 首页 */
        HOMEPAGE
    }

    /** 跳转界面界面类型 */
    public enum PosterType
    {
        /** 外链 */
        LINK,
        /** 应用内容详情 */
        CONTENT
    }

    /** 海报位置 */
    private PosterPosition posterPosition;

    /** 海报标题 */
    private String posterTitle;

    /** 海报封面（640 * 398） */
    private String posterCover;

    /** 海报查看次数 */
    private Long viewCount;

    /** 跳转界面类型 */
    private PosterType posterType;

    /** 外联地址 */
    private String url;

    /** 内容详情 */
    private String content;

    /** true上架、fale下架 默认上架 */
    private Boolean status;

    private DictSchool dictSchool;

    /**
     * @return 返回 posterPosition
     */
    public PosterPosition getPosterPosition()
    {
        return posterPosition;
    }

    /**
     * @param 对posterPosition进行赋值
     */
    public void setPosterPosition(PosterPosition posterPosition)
    {
        this.posterPosition = posterPosition;
    }

    /**
     * @return 返回 posterTitle
     */
    public String getPosterTitle()
    {
        return posterTitle;
    }

    /**
     * @param 对posterTitle进行赋值
     */
    public void setPosterTitle(String posterTitle)
    {
        this.posterTitle = posterTitle;
    }

    /**
     * @return 返回 posterCover
     */
    public String getPosterCover()
    {
        return posterCover;
    }

    /**
     * @param 对posterCover进行赋值
     */
    public void setPosterCover(String posterCover)
    {
        this.posterCover = posterCover;
    }

    /**
     * @return 返回 viewCount
     */
    public Long getViewCount()
    {
        return viewCount;
    }

    /**
     * @param 对viewCount进行赋值
     */
    public void setViewCount(Long viewCount)
    {
        this.viewCount = viewCount;
    }

    /**
     * @return 返回 posterType
     */
    public PosterType getPosterType()
    {
        return posterType;
    }

    /**
     * @param 对posterType进行赋值
     */
    public void setPosterType(PosterType posterType)
    {
        this.posterType = posterType;
    }

    /**
     * @return 返回 url
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * @param 对url进行赋值
     */
    public void setUrl(String url)
    {
        this.url = url;
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
     * @return 返回 status
     */
    public Boolean getStatus()
    {
        return status;
    }

    /**
     * @param 对status进行赋值
     */
    public void setStatus(Boolean status)
    {
        this.status = status;
    }

    /**
     * @return 返回 dictSchool
     */
    @NotNull
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
