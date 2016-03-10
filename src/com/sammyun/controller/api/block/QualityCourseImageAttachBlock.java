package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "qualityCourseImageAttachBlock")
public class QualityCourseImageAttachBlock
{
    private Long id;
    
    /** 图片附件 */
    private String imageAttach;

    /** 图片描述 */
    private String description;

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
    public String getImageAttach()
    {
        return imageAttach;
    }

    public void setImageAttach(String imageAttach)
    {
        this.imageAttach = imageAttach;
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

    
}
