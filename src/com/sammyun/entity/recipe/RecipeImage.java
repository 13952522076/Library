package com.sammyun.entity.recipe;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;

/**
 * Dao - 食谱图片
 * 
 * @author Sencloud Team

 */

@Entity
@Table(name = "t_pe_recipe_image")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_recipe_image_sequence")
public class RecipeImage extends BaseEntity
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -7547145828736068789L;

    /** 菜名称 */
    // private String dishName;

    /** 菜图片 */
    private String dishImage;

    /** 菜描述 */
    // private String description;

    /***/
    private RecipeSection recipeSection;

    /**
     * @return 返回 dishImage
     */
    @JsonProperty
    public String getDishImage()
    {
        return dishImage;
    }

    /**
     * @param 对dishImage进行赋值
     */
    public void setDishImage(String dishImage)
    {
        this.dishImage = dishImage;
    }

    /**
     * @return 返回 recipeSection
     */
    @NotNull
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public RecipeSection getRecipeSection()
    {
        return recipeSection;
    }

    /**
     * @param 对recipeSection进行赋值
     */
    public void setRecipeSection(RecipeSection recipeSection)
    {
        this.recipeSection = recipeSection;
    }

}
