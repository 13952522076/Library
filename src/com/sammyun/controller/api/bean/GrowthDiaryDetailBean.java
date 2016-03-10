package com.sammyun.controller.api.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "growthDiaryDetailBean")
public class GrowthDiaryDetailBean
{
    /** 用户id */
    private Long memberId;

    /** 成长记id */
    private Long growthDiaryId;

    @XmlElement
    public Long getMemberId()
    {
        return memberId;
    }

    public void setMemberId(Long memberId)
    {
        this.memberId = memberId;
    }

    @XmlElement
    public Long getGrowthDiaryId()
    {
        return growthDiaryId;
    }

    public void setGrowthDiaryId(Long growthDiaryId)
    {
        this.growthDiaryId = growthDiaryId;
    }

}
