package com.sammyun.controller.api.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.controller.api.model.PageModel;

@XmlRootElement(name = "growthDiaryShareBean")
public class GrowthDiaryShareBean
{
    /** 分享人id */
    private Long memberId;

    /** 分享平台 */
    private String shared;

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
    public String getShared()
    {
        return shared;
    }

    public void setShared(String shared)
    {
        this.shared = shared;
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
