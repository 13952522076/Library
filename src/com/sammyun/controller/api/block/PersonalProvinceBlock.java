package com.sammyun.controller.api.block;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "profileProvinceBlock")
public class PersonalProvinceBlock
{
    /**省份名称*/
    private String name;
    
    /** 省份id*/
    private Long id;
    
    /**下属包含的所有城市*/
    private List <PersonalProvinceBlock> citys;

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
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @XmlElement
    public List<PersonalProvinceBlock> getCitys()
    {
        return citys;
    }

    public void setCitys(List<PersonalProvinceBlock> citys)
    {
        this.citys = citys;
    }
    
    
}
