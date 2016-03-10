package com.sammyun.controller.api.block;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "recipeWeekDayBlock")
public class RecipeWeekDayBlock
{
    /** 食谱id */
    private Long id;
    
    /** 周几 */
    private Long weekDay;
    
    /** 学生食谱-食谱段 */
    private List<RecipeSectionBlock> recipeSections;

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
    public Long getWeekDay()
    {
        return weekDay;
    }

    public void setWeekDay(Long weekDay)
    {
        this.weekDay = weekDay;
    }

    @XmlElement
    public List<RecipeSectionBlock> getRecipeSections()
    {
        return recipeSections;
    }

    public void setRecipeSections(List<RecipeSectionBlock> recipeSections)
    {
        this.recipeSections = recipeSections;
    }
    
    
}
