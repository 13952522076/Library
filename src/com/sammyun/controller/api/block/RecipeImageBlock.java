package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "recipeDetailBlock")
public class RecipeImageBlock
{
    /** 菜id */
    private Long id;

    /** 菜图片 */
    private String dishImage;

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
    public String getDishImage()
    {
        return dishImage;
    }

    public void setDishImage(String dishImage)
    {
        this.dishImage = dishImage;
    }

}
