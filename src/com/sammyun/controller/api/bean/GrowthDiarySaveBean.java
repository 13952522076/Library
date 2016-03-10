package com.sammyun.controller.api.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "growthDiarySaveTextBean")
public class GrowthDiarySaveBean
{
    /** 用户id */
    private Long memberId;

    /** 成长记描述 */
    private String diaryMsg;

    /** 成长记地址 */
    private String address;
    
    /** 图片地址数组 */
    private List<String> images;
    
    /** 成长记标签id数组*/
    private List<Long> diaryTagIds;
    
    /** android版的语音文件采用amr格式 */
    private String amrUrl;

    /** iPhone版采用aud格式 */
    private String audUrl;

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
    public String getDiaryMsg()
    {
        return diaryMsg;
    }

    public void setDiaryMsg(String diaryMsg)
    {
        this.diaryMsg = diaryMsg;
    }

    @XmlElement
    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @XmlElement
    public List<String> getImages()
    {
        return images;
    }

    public void setImages(List<String> images)
    {
        this.images = images;
    }

    @XmlElement
    public List<Long> getDiaryTagIds()
    {
        return diaryTagIds;
    }

    public void setDiaryTagIds(List<Long> diaryTagIds)
    {
        this.diaryTagIds = diaryTagIds;
    }

    @XmlElement
    public String getAmrUrl()
    {
        return amrUrl;
    }

    public void setAmrUrl(String amrUrl)
    {
        this.amrUrl = amrUrl;
    }

    @XmlElement
    public String getAudUrl()
    {
        return audUrl;
    }

    public void setAudUrl(String audUrl)
    {
        this.audUrl = audUrl;
    }
    
}
