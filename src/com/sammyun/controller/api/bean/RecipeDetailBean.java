package com.sammyun.controller.api.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "recipeDetailBean")
public class RecipeDetailBean
{
    /** 班级id */
    private Long dictSchoolId;

    /** 年 */
    private Long year;

    /** 月 */
    private Long month;
    
    /** 周 */
    private Long week;

    @XmlElement
    public Long getDictSchoolId()
    {
        return dictSchoolId;
    }

    public void setDictSchoolId(Long dictSchoolId)
    {
        this.dictSchoolId = dictSchoolId;
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

    public Long getWeek()
    {
        return week;
    }

    @XmlElement
    public void setWeek(Long week)
    {
        this.week = week;
    }
    
    
}
