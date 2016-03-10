package com.sammyun.controller.api.block;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "growthDiaryListBlock")
public class GrowthDiaryListBlock
{
    /** 好友id */
    private Long memberId;

    /** 好友头像 */
    private String iconPhoto;

    /** 好友真实姓名 */
    private String realName;

    /** 成长记id */
    private Long diaryId;

    /** 成长记图片地址 */
    private List<String> images = new ArrayList<String>();

    /** 成长记的创建时间 */
    private String createDate;

    /** 日誌内容 */
    private String diaryMsg;

    /** 成长记分享次数 */
    private Integer transpondCount;

    /** 阅读次数 */
    private Integer readCount;
    
    /** 播放次数 */
    private Integer playCount;

    /** 当前用户是否对此条点过赞 */
    private Boolean isAgree;

    /** android版的语音文件采用amr格式 */
    private String amrUrl;

    /** iPhone版采用aud格式 */
    private String audUrl;
    
    /** 当前用户是否有权限删除 */
    private Boolean deleteable;
    
    /** 当前用户是否转发过此日记 */
    private Boolean transponded;

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
    public Long getDiaryId()
    {
        return diaryId;
    }

    public void setDiaryId(Long diaryId)
    {
        this.diaryId = diaryId;
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
    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
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
    public Integer getTranspondCount()
    {
        return transpondCount;
    }

    public void setTranspondCount(Integer transpondCount)
    {
        this.transpondCount = transpondCount;
    }

    @XmlElement
    public Integer getReadCount()
    {
        return readCount;
    }

    public void setReadCount(Integer readCount)
    {
        this.readCount = readCount;
    }
    
    @XmlElement
    public Integer getPlayCount()
    {
        return playCount;
    }

    public void setPlayCount(Integer playCount)
    {
        this.playCount = playCount;
    }

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
