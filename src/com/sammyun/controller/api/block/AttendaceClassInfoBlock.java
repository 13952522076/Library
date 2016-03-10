package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "classInfoBlock")
public class AttendaceClassInfoBlock
{
    /** 班级id */
    private Long id;

    /** 班级名 */
    private String name;

    /** 班主任 */
    private String cmaster;

    /** 描述 */
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
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @XmlElement
    public String getCmaster()
    {
        return cmaster;
    }

    public void setCmaster(String cmaster)
    {
        this.cmaster = cmaster;
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
