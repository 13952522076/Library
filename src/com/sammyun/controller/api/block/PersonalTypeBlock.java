package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.entity.dict.PatriarchStudentMap.Type;

@XmlRootElement(name = "profileTypeBlock")
public class PersonalTypeBlock
{
    /**类型名称*/
    private String typeName;
    
    /** 类型*/
    private Type type;

    @XmlElement
    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    @XmlElement
    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

}
