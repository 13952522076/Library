package com.sammyun.entity.profile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.dict.DictSchool;

/**
 * Entity - 学校概况数据
 * 
 * @author Sencloud Team

 */
@Entity
@Table(name = "t_pe_profile")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_profiles_sequence")
public class Profile extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -3143238575633239040L;

    /** 概况首页 */
    private String indexImage;

    /** 校徽 */
    private String schoolBadge;

    /** 学校简介 */
    private String introduction;

    /** 校训 */
    private String schoolMotto;

    /** 校歌 */
    private String schoolSong;

    /** 园长寄语 */
    private String sendWord;

    /** 隶属学校 */
    private DictSchool dictSchool;

    /**
     * @return 返回 indexImage
     */
    @Column(name = "index_image", length = 255)
    public String getIndexImage()
    {
        return indexImage;
    }

    /**
     * @param 对indexImage进行赋值
     */
    public void setIndexImage(String indexImage)
    {
        this.indexImage = indexImage;
    }

    @Column(name = "introduction", length = 65535)
    public String getIntroduction()
    {
        return this.introduction;
    }

    public void setIntroduction(String introduction)
    {
        this.introduction = introduction;
    }

    @Column(name = "school_badge", length = 65535)
    public String getSchoolBadge()
    {
        return this.schoolBadge;
    }

    public void setSchoolBadge(String schoolBadge)
    {
        this.schoolBadge = schoolBadge;
    }

    @Column(name = "school_motto", length = 65535)
    public String getSchoolMotto()
    {
        return this.schoolMotto;
    }

    public void setSchoolMotto(String schoolMotto)
    {
        this.schoolMotto = schoolMotto;
    }

    @Column(name = "school_song", length = 65535)
    public String getSchoolSong()
    {
        return this.schoolSong;
    }

    public void setSchoolSong(String schoolSong)
    {
        this.schoolSong = schoolSong;
    }

    @Column(name = "send_word", length = 1000)
    public String getSendWord()
    {
        return sendWord;
    }

    public void setSendWord(String sendWord)
    {
        this.sendWord = sendWord;
    }

    /**
     * @return 返回 dictSchool
     */
    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public DictSchool getDictSchool()
    {
        return dictSchool;
    }

    /**
     * @param 对dictSchool进行赋值
     */
    public void setDictSchool(DictSchool dictSchool)
    {
        this.dictSchool = dictSchool;
    }
}
