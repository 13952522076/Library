package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "diaryTagListBlock")
public class DiaryTagListBlock
{
    /** 标签id */
    private Long diaryTagId;

    /** 标签名称 */
    private String name;

    @XmlElement
    public Long getDiaryTagId()
    {
        return diaryTagId;
    }

    public void setDiaryTagId(Long diaryTagId)
    {
        this.diaryTagId = diaryTagId;
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

}
