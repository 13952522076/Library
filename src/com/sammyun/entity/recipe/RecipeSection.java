package com.sammyun.entity.recipe;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.OrderEntity;

/**
 * Entity - 学生食谱-食谱段
 * 


 */
@Entity
@Table(name = "t_pe_recipe_section")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_recipe_section_sequence")
public class RecipeSection extends OrderEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 9165213912577869088L;

    /** 食谱段（早餐、加餐、午餐、午点心、晚餐） */
    private String sectionName;

    private String description;

    /** 食谱周信息 */
    private RecipeWeekDay recipeWeekDay;

    /** 学生食谱 */
    private List<RecipeImage> recipeImages = new ArrayList<RecipeImage>();

    /**
     * @return 返回 sectionName
     */
    @NotNull
    @JsonProperty
    @Column(name = "section_name", nullable = false, length = 50)
    public String getSectionName()
    {
        return this.sectionName;
    }

    /**
     * @param 设置sectionName
     */
    public void setSectionName(String sectionName)
    {
        this.sectionName = sectionName;
    }

    /**
     * @return 返回 description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param 对description进行赋值
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return 返回 recipeWeekDay
     */
    @NotNull
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public RecipeWeekDay getRecipeWeekDay()
    {
        return recipeWeekDay;
    }

    /**
     * @param 设置recipeWeekDay
     */
    public void setRecipeWeekDay(RecipeWeekDay recipeWeekDay)
    {
        this.recipeWeekDay = recipeWeekDay;
    }

    /**
     * @return 返回 recipeDetails
     */
    @OneToMany(mappedBy = "recipeSection", fetch = FetchType.LAZY, cascade = {CascadeType.ALL},orphanRemoval = true)
    public List<RecipeImage> getRecipeImages()
    {
        return recipeImages;
    }

    /**
     * @param 设置recipeDetails
     */
    public void setRecipeImages(List<RecipeImage> recipeImages)
    {
        this.recipeImages = recipeImages;
    }

}
