package com.sammyun.controller.api.block;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "recipeSectionBlock")
public class RecipeSectionBlock
{
    /** 食谱id */
    private Long id;
    
    /** 食谱段（早餐、加餐、午餐、午点心、晚餐） */
    private String sectionName;
    
    /** 菜描述 */
    private String description;

    /** 学生食谱 */
    private List<RecipeImageBlock> recipeImages;

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
    public String getSectionName()
    {
        return sectionName;
    }

    public void setSectionName(String sectionName)
    {
        this.sectionName = sectionName;
    }

    @XmlElement
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @XmlElement
    public List<RecipeImageBlock> getRecipeImages()
    {
        return recipeImages;
    }

    public void setRecipeImages(List<RecipeImageBlock> recipeImages)
    {
        this.recipeImages = recipeImages;
    }
    
}
