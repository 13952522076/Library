package com.sammyun.entity.news;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.dict.DictSchool;

/**
 * Entity - 新闻类别
 * 
 * @author xutianlong
 * @version [版本号, Apr 21, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_news_category")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_news_category_sequence")
public class NewsCategory extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -1053667168483439595L;

    /** 新闻类别 名称 */
    private String categoryName;

    /** 删除标记（0未删除，1删除） */
    private Integer defFlag;

    /** 状态（0，上架 1，下架） */
    private Integer status;

    /** 隶属的学校 */
    private DictSchool dictSchool;

    /** 隶属的新闻类别 */
    private Set<News> newses = new HashSet<News>();

    /**
     * @return 返回 categoryName
     */
    @NotNull
    @Column(name = "categoryName", length = 40)
    public String getCategoryName()
    {
        return categoryName;
    }

    /**
     * @param 对categoryName进行赋值
     */
    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    /**
     * 删除标记（0未删除，1删除）
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Column(name = "def_flag", columnDefinition = "int default 0")
    public Integer getDefFlag()
    {
        return this.defFlag;
    }

    public void setDefFlag(Integer defFlag)
    {
        this.defFlag = defFlag;
    }

    @Column(name = "status", columnDefinition = "int default 0")
    public Integer getStatus()
    {
        return this.status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
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

    /**
     * @return 返回 newses
     */
    @OneToMany(mappedBy = "newsCategory", fetch = FetchType.LAZY)
    public Set<News> getNewses()
    {
        return newses;
    }

    /**
     * @param 对newses进行赋值
     */
    public void setNewses(Set<News> newses)
    {
        this.newses = newses;
    }
}
