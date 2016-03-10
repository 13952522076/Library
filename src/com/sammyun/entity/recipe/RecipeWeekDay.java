package com.sammyun.entity.recipe;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;

/**
 * Entity - 学生食谱周信息
 * 
 * @author Sencloud Team

 */
@Entity
@Table(name = "t_pe_recipe_week_day")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_recipe_week_day_sequence")
public class RecipeWeekDay extends BaseEntity
{

    private static final long serialVersionUID = 8083645408712621822L;

    /** 周几 */
    private Long weekDay;

    /** 学生食谱 */
    private Recipe recipe;
    
    /** 学生食谱-食谱段 */
    private List<RecipeSection> recipeSections = new ArrayList<RecipeSection>();

    /**
     * @return 返回recipe
     */
    @NotNull
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Recipe getRecipe()
    {
        return recipe;
    }

    
    /**
     * @param 设置recipe 
     */
    public void setRecipe(Recipe recipe)
    {
        this.recipe = recipe;
    }

    /**
     * @return 返回weekDay
     */
    @NotNull
    public Long getWeekDay()
    {
        return weekDay;
    }

    /**
     * @param 设置weekDay 
     */
    public void setWeekDay(Long weekDay)
    {
        this.weekDay = weekDay;
    }

    /**
     * @return 返回recipeSections
     */
    @OneToMany(mappedBy = "recipeWeekDay", fetch = FetchType.LAZY, cascade = {CascadeType.ALL},orphanRemoval = true)
    public List<RecipeSection> getRecipeSections()
    {
        return recipeSections;
    }

    /**
     * @param 设置recipeSections 
     */
    public void setRecipeSections(List<RecipeSection> recipeSections)
    {
        this.recipeSections = recipeSections;
    }

}
