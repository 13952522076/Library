package com.sammyun.entity.recipe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.dict.DictSchool;
import com.sun.istack.internal.NotNull;

/**
 * Entity - 学生食谱
 * 


 */
@Entity
@Table(name = "t_pe_recipe")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_recipe_sequence")
public class Recipe extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -2093034960957292253L;

    /** 年 */
    private Long year;

    /** 月 */
    private Long month;

    /** 第几周 */
    private Long week;

    /** 是否是当前周 */
    private Boolean isCurrent;

    /** 开始时间 */
    private Date weekStartDate;

    /** 结束时间 */
    private Date weekEndDate;

    /** 隶属的学校 */
    private DictSchool dictSchool;

    /** 食谱详情 */
    private List<RecipeWeekDay> recipeWeekDays = new ArrayList<RecipeWeekDay>();

    /**
     * @return 返回 dictSchool
     */
    @NotNull
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
     * @return 返回 year
     */
    @NotNull
    public Long getYear()
    {
        return year;
    }

    /**
     * @param 设置year
     */
    public void setYear(Long year)
    {
        this.year = year;
    }

    /**
     * @return 返回 month
     */
    @NotNull
    public Long getMonth()
    {
        return month;
    }

    /**
     * @param 设置month
     */
    public void setMonth(Long month)
    {
        this.month = month;
    }

    /**
     * @return 返回 week
     */
    @NotNull
    public Long getWeek()
    {
        return week;
    }

    /**
     * @param 设置week
     */
    public void setWeek(Long week)
    {
        this.week = week;
    }

    /**
     * @return 返回 isCurrent
     */
    public Boolean getIsCurrent()
    {
        return isCurrent;
    }

    /**
     * @param 设置isCurrent
     */
    public void setIsCurrent(Boolean isCurrent)
    {
        this.isCurrent = isCurrent;
    }

    /**
     * @return 返回 weekStartDate
     */
    @NotNull
    public Date getWeekStartDate()
    {
        return weekStartDate;
    }

    /**
     * @param 设置weekStartDate
     */
    public void setWeekStartDate(Date weekStartDate)
    {
        this.weekStartDate = weekStartDate;
    }

    /**
     * @return 返回 weekEndDate
     */
    @NotNull
    public Date getWeekEndDate()
    {
        return weekEndDate;
    }

    /**
     * @param 设置weekEndDate
     */
    public void setWeekEndDate(Date weekEndDate)
    {
        this.weekEndDate = weekEndDate;
    }

    /**
     * 学生食谱周信息
     * 
     * @return 会员
     */
    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY,cascade={CascadeType.ALL},orphanRemoval = true)
    public List<RecipeWeekDay> getRecipeWeekDays()
    {
        return recipeWeekDays;
    }

    /**
     * @param 对recipeWeekDays进行赋值
     */
    public void setRecipeWeekDays(List<RecipeWeekDay> recipeWeekDays)
    {
        this.recipeWeekDays = recipeWeekDays;
    }

}
