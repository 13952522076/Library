package com.sammyun.controller.api.block;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "growthDiaryDetailBlock")
public class GrowthDiaryDetailBlock
{
    /** 当前用户是否点赞 */
    private Boolean isAgree;

    /** 图片地址数组 */
    private List<String> images = new ArrayList<String>();

    /** android版的语音文件采用amr格式 */
    private String amrUrl;

    /** iPhone版采用aud格式 */
    private String audUrl;

    /** 发布人头像 */
    private String iconPhoto;

    /** 发布人姓名 */
    private String realName;

    /** 分享数 */
    private Integer transpondCount;

    /** 发布时间 */
    private String createDate;

    /** 喜欢数 */
    private Integer agreeCount;

    /** 日誌内容 */
    private String diaryMsg;

    /** 喜欢人的头像数组 */
    private List<String> agreeIconPhotoes = new ArrayList<String>();

    /** 地址 */
    private String address;

    /** 标签名字数组 */
    private List<String> diaryTagNames;

    /** 当前用户是否有权限删除 */
    private Boolean deleteable;

    /** 当前用户是否转发过此日记 */
    private Boolean transponded;

    @XmlElement
    public Boolean getIsAgree()
    {
        return isAgree;
    }

    public void setIsAgree(Boolean isAgree)
    {
        this.isAgree = isAgree;
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

    @XmlElement
    public String getIconPhoto()
    {
        return iconPhoto;
    }

    public void setIconPhoto(String iconPhoto)
    {
        this.iconPhoto = iconPhoto;
    }

    @XmlElement
    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    @XmlElement
    public Integer getTranspondCount()
    {
        return transpondCount;
    }

    public void setTranspondCount(Integer transpondCount)
    {
        this.transpondCount = transpondCount;
    }

    @XmlElement
    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    @XmlElement
    public Integer getAgreeCount()
    {
        return agreeCount;
    }

    public void setAgreeCount(Integer agreeCount)
    {
        this.agreeCount = agreeCount;
    }

    @XmlElement
    public List<String> getAgreeIconPhotoes()
    {
        return agreeIconPhotoes;
    }

    public void setAgreeIconPhotoes(List<String> agreeIconPhotoes)
    {
        this.agreeIconPhotoes = agreeIconPhotoes;
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
    public List<String> getDiaryTagNames()
    {
        return diaryTagNames;
    }

    public void setDiaryTagNames(List<String> diaryTagNames)
    {
        this.diaryTagNames = diaryTagNames;
    }

    @XmlElement
    public Boolean getDeleteable()
    {
        return deleteable;
    }

    public void setDeleteable(Boolean deleteable)
    {
        this.deleteable = deleteable;
    }

    @XmlElement
    public Boolean getTransponded()
    {
        return transponded;
    }

    public void setTransponded(Boolean transponded)
    {
        this.transponded = transponded;
    }
}
