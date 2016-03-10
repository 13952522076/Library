package com.sammyun.controller.api.block;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "recipeBlock")
public class RecipeListBlock
{
    /** 食谱id */
    private Long id;
    
    /** 年 */
    private Long year;

    /** 月 */
    private Long month;

    /** 第几周 */
    private Long week;
    
    /** 开始时间 */
    private String createDate;
    
    /** 是否是当前周 */
    private Boolean isCurrent;

    /** 开始时间 */
    private String weekStartDate;

    /** 结束时间 */
    private String weekEndDate;
    
    /** 学生食谱 */
    private List<RecipeWeekDayBlock> recipeWeekDays;

    @XmlElement
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @XmlElement
    public Long getYear()
    {
        return year;
    }

    public void setYear(Long year)
    {
        this.year = year;
    }

    @XmlElement
    public Long getMonth()
    {
        return month;
    }

    public void setMonth(Long month)
    {
        this.month = month;
    }

    @XmlElement
    public Long getWeek()
    {
        return week;
    }

    public void setWeek(Long week)
    {
        this.week = week;
    }
    
    @XmlElement
    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    @XmlElement
    public Boolean getIsCurrent()
    {
        return isCurrent;
    }

    public void setIsCurrent(Boolean isCurrent)
    {
        this.isCurrent = isCurrent;
    }

    @XmlElement
    public String getWeekStartDate()
    {
        return weekStartDate;
    }

    public void setWeekStartDate(String weekStartDate)
    {
        this.weekStartDate = weekStartDate;
    }

    @XmlElement
    public String getWeekEndDate()
    {
        return weekEndDate;
    }

    public void setWeekEndDate(String weekEndDate)
    {
        this.weekEndDate = weekEndDate;
    }

    @XmlElement
    public List<RecipeWeekDayBlock> getRecipeWeekDays()
    {
        return recipeWeekDays;
    }

    public void setRecipeWeekDays(List<RecipeWeekDayBlock> recipeWeekDays)
    {
        this.recipeWeekDays = recipeWeekDays;
    }
    
    
}
