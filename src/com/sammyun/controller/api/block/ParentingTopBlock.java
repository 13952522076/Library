package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "parentingCategoryBlock")
public class ParentingTopBlock
{
    /** id */
    private Long id;

    /** 图标文件标识 */
    private String smallIconfile;

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
    public String getSmallIconfile()
    {
        return smallIconfile;
    }

    public void setSmallIconfile(String smallIconfile)
    {
        this.smallIconfile = smallIconfile;
    }
}
