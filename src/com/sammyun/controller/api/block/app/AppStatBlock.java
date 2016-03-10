package com.sammyun.controller.api.block.app;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "appStatBlock")
public class AppStatBlock
{

    /** app应用ID */
    private Long id;

    /** 评价得分 */
    private Double avgRating;

    /** 得分数量 */
    private Integer countRating;

    /** 应用安装的用户量 */
    private Integer countUser;

    /** 应用查看的数量 */
    private Integer countView;

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
    public Double getAvgRating()
    {
        return avgRating;
    }

    public void setAvgRating(Double avgRating)
    {
        this.avgRating = avgRating;
    }

    @XmlElement
    public Integer getCountRating()
    {
        return countRating;
    }

    public void setCountRating(Integer countRating)
    {
        this.countRating = countRating;
    }

    @XmlElement
    public Integer getCountUser()
    {
        return countUser;
    }

    public void setCountUser(Integer countUser)
    {
        this.countUser = countUser;
    }

    @XmlElement
    public Integer getCountView()
    {
        return countView;
    }

    public void setCountView(Integer countView)
    {
        this.countView = countView;
    }

}
