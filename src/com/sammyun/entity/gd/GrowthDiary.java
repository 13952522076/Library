package com.sammyun.entity.gd;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.Member;

/**
 * Entity - 成长日记
 * 
 * @author xutianlong
 * @version 3.0
 */
@Entity
@Table(name = "t_pe_growth_diary")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_growth_diary_sequence")
public class GrowthDiary extends BaseEntity
{

    /**
     * 
     */
    private static final long serialVersionUID = 3735012997666242915L;

    /** 点击数缓存名称 */
    public static final String HITS_CACHE_NAME = "growthDiaryHits";
    
    /** 播放次数缓存名称 */
    public static final String PLAY_CACHE_NAME = "growthDiaryPlays";

    /** 点击数缓存更新间隔时间 */
    public static final int HITS_CACHE_INTERVAL = 60000;
    
    /** 播放数混存更新间隔时间 */
    public static final int PLAY_CACHE_INTERVAL = 60000;

    /***
     * 成长日记類型
     */
    public enum DiaryType
    {
        /**
         * 小視頻 存在視頻格式（mp4），認定為video
         */
        video,

        /**
         * 相片 只存在圖片，認定為photo
         */
        photo,

        /**
         * 語音 如果存在语音 amr 和 aud，类型认定为语音 的android版的语音文件采用amr格式，而iPhone版采用aud格式
         */
        speech,

        /**
         * 文字 只包含文字的
         */
        words
    }

    /** 成长日记類型 */
    private DiaryType diaryType;

    /** android版的语音文件采用amr格式 */
    private String amrUrl;

    /** iPhone版采用aud格式 */
    private String audUrl;

    /** 日誌内容 */
    private String diaryMsg;

    /** 收藏次数 */
    private Integer collectCount;

    /** 评论次数 */
    private Integer commentCount;

    /** 转发次数 */
    private Integer transpondCount;

    /** 赞同次数 */
    private Integer agreeCount;

    /** 阅读次数 */
    private Integer readCount;

    /** 播放次数 */
    private Integer playCount;

    /** 日誌标签 */
    private String label;

    /** 地理位置 */
    private String address;

    /** 是否禁止評論 true 不可以評論，false可以評論，默認false */
    private Boolean prohibitionComments;

    /** 是否發佈到微信朋友圈 */
    private Boolean publishWeChat;

    /** 发表人 */
    private Member member;

    /** 转发 */
    private Set<DiaryTranspond> diaryTransponds = new HashSet<DiaryTranspond>();

    /** 成長日記圖片附件 */
    private Set<DiaryPicture> diaryPictures = new HashSet<DiaryPicture>();

    /** 成长记评论 */
    private Set<DiaryComment> diaryComments = new HashSet<DiaryComment>();

    /** 成長日記收藏 */
    private Set<DiaryCollection> diaryCollections = new HashSet<DiaryCollection>();

    /** 成長日記点赞 */
    private Set<DiaryAgree> diaryAgrees = new HashSet<DiaryAgree>();

    /** 成長日記At到用戶列表 */
    private Set<AtUser> atUsers = new HashSet<AtUser>();

    /** 日志标签 */
    private Set<DiaryTag> diaryTags = new HashSet<DiaryTag>();
    

    /**
     * @return the diaryType
     */
    public DiaryType getDiaryType()
    {
        return diaryType;
    }

    /**
     * @param diaryType the diaryType to set
     */
    public void setDiaryType(DiaryType diaryType)
    {
        this.diaryType = diaryType;
    }

    /**
     * @return the amrUrl
     */
    public String getAmrUrl()
    {
        return amrUrl;
    }

    /**
     * @param amrUrl the amrUrl to set
     */
    public void setAmrUrl(String amrUrl)
    {
        this.amrUrl = amrUrl;
    }

    /**
     * @return the audUrl
     */
    public String getAudUrl()
    {
        return audUrl;
    }

    /**
     * @param audUrl the audUrl to set
     */
    public void setAudUrl(String audUrl)
    {
        this.audUrl = audUrl;
    }

    /**
     * @return the diaryMsg
     */
    public String getDiaryMsg()
    {
        return diaryMsg;
    }

    /**
     * @param diaryMsg the diaryMsg to set
     */
    public void setDiaryMsg(String diaryMsg)
    {
        this.diaryMsg = diaryMsg;
    }

    /**
     * @return the collectCount
     */
    public Integer getCollectCount()
    {
        return collectCount;
    }

    /**
     * @param collectCount the collectCount to set
     */
    public void setCollectCount(Integer collectCount)
    {
        this.collectCount = collectCount;
    }

    /**
     * @return the commentCount
     */
    public Integer getCommentCount()
    {
        return commentCount;
    }

    /**
     * @param commentCount the commentCount to set
     */
    public void setCommentCount(Integer commentCount)
    {
        this.commentCount = commentCount;
    }

    /**
     * @return the transpondCount
     */
    public Integer getTranspondCount()
    {
        return transpondCount;
    }

    /**
     * @param transpondCount the transpondCount to set
     */
    public void setTranspondCount(Integer transpondCount)
    {
        this.transpondCount = transpondCount;
    }

    /**
     * @return the agreeCount
     */
    public Integer getAgreeCount()
    {
        return agreeCount;
    }

    /**
     * @param agreeCount the agreeCount to set
     */
    public void setAgreeCount(Integer agreeCount)
    {
        this.agreeCount = agreeCount;
    }

    /**
     * @return the readCount
     */
    public Integer getReadCount()
    {
        return readCount;
    }

    /**
     * @param readCount the readCount to set
     */
    public void setReadCount(Integer readCount)
    {
        this.readCount = readCount;
    }

    /**
     * @return the playCount
     */
    public Integer getPlayCount()
    {
        return playCount;
    }

    /**
     * @param playCount the playCount to set
     */
    public void setPlayCount(Integer playCount)
    {
        this.playCount = playCount;
    }

    /**
     * @return the label
     */
    public String getLabel()
    {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label)
    {
        this.label = label;
    }

    /**
     * @return the address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * @return 返回 prohibitionComments
     */
    public Boolean getProhibitionComments()
    {
        return prohibitionComments;
    }

    /**
     * @param 对prohibitionComments进行赋值
     */
    public void setProhibitionComments(Boolean prohibitionComments)
    {
        this.prohibitionComments = prohibitionComments;
    }

    /**
     * @return 返回 publishWeChat
     */
    public Boolean getPublishWeChat()
    {
        return publishWeChat;
    }

    /**
     * @param 对publishWeChat进行赋值
     */
    public void setPublishWeChat(Boolean publishWeChat)
    {
        this.publishWeChat = publishWeChat;
    }

    /**
     * @return 返回 member
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Member getMember()
    {
        return member;
    }

    /**
     * @param 对member进行赋值
     */
    public void setMember(Member member)
    {
        this.member = member;
    }

    /**
     * @return 返回 diaryTransponds
     */
    @OneToMany(mappedBy = "growthDiary", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<DiaryTranspond> getDiaryTransponds()
    {
        return diaryTransponds;
    }

    /**
     * @param 对diaryTransponds进行赋值
     */
    public void setDiaryTransponds(Set<DiaryTranspond> diaryTransponds)
    {
        this.diaryTransponds = diaryTransponds;
    }

    /**
     * @return 返回 diaryPictures
     */
    @OneToMany(mappedBy = "growthDiary", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<DiaryPicture> getDiaryPictures()
    {
        return diaryPictures;
    }

    /**
     * @param 对diaryPictures进行赋值
     */
    public void setDiaryPictures(Set<DiaryPicture> diaryPictures)
    {
        this.diaryPictures = diaryPictures;
    }

    /**
     * @return 返回 diaryComments
     */
    @OneToMany(mappedBy = "growthDiary", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<DiaryComment> getDiaryComments()
    {
        return diaryComments;
    }

    /**
     * @param 对diaryPictures进行赋值
     */
    public void setDiaryComments(Set<DiaryComment> diaryComments)
    {
        this.diaryComments = diaryComments;
    }

    /**
     * @return 返回 diaryComments
     */
    @OneToMany(mappedBy = "growthDiary", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<DiaryCollection> getDiaryCollections()
    {
        return diaryCollections;
    }

    /**
     * @param 对diaryCollections进行赋值
     */
    public void setDiaryCollections(Set<DiaryCollection> diaryCollections)
    {
        this.diaryCollections = diaryCollections;
    }

    /**
     * @return 返回 diaryAgrees
     */
    @OneToMany(mappedBy = "growthDiary", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<DiaryAgree> getDiaryAgrees()
    {
        return diaryAgrees;
    }

    /**
     * @param 对diaryAgrees进行赋值
     */
    public void setDiaryAgrees(Set<DiaryAgree> diaryAgrees)
    {
        this.diaryAgrees = diaryAgrees;
    }

    /**
     * @return 返回 atUsers
     */
    @OneToMany(mappedBy = "growthDiary", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<AtUser> getAtUsers()
    {
        return atUsers;
    }

    /**
     * @param 对atUsers进行赋值
     */
    public void setAtUsers(Set<AtUser> atUsers)
    {
        this.atUsers = atUsers;
    }

    /**
     * @return
     * @see [类、类#方法、类#成员]
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_pe_growth_diary_tag")
    @OrderBy("createDate asc")
    public Set<DiaryTag> getDiaryTags()
    {
        return diaryTags;
    }

    /**
     * @param 对diaryTags进行赋值
     */
    public void setDiaryTags(Set<DiaryTag> diaryTags)
    {
        this.diaryTags = diaryTags;
    }

}
