package com.sammyun.controller.api.block;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "qualityCourseListBlock")
public class QualityCourseListBlock
{
    private Long id;
    
    /** 图片描述 */
    private String description;

    /** 相册图片*/
    private List<QualityCourseImageAttachBlock> qualityCourseImageAttachs;

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
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @XmlElement
    public List<QualityCourseImageAttachBlock> getQualityCourseImageAttachs()
    {
        return qualityCourseImageAttachs;
    }

    public void setQualityCourseImageAttachs(List<QualityCourseImageAttachBlock> qualityCourseImageAttachs)
    {
        this.qualityCourseImageAttachs = qualityCourseImageAttachs;
    }
    
}
