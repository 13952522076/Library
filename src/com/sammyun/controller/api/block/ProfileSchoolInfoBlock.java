package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "profileSchoolInfoBlock")
public class ProfileSchoolInfoBlock
{
    /** 概况首页 */
    private String indexImage;
    
    /** 校徽 */
    private String schoolBadge;

    /** 校训 */
    private String schoolMotto;
    
    /** 学校简介 */
    private String introduction;

    /** 校歌 */
    private String schoolSong;

    @XmlElement
    public String getIndexImage()
    {
        return indexImage;
    }

    public void setIndexImage(String indexImage)
    {
        this.indexImage = indexImage;
    }

    @XmlElement
    public String getIntroduction()
    {
        return introduction;
    }

    public void setIntroduction(String introduction)
    {
        this.introduction = introduction;
    }

    @XmlElement
    public String getSchoolBadge()
    {
        return schoolBadge;
    }

    public void setSchoolBadge(String schoolBadge)
    {
        this.schoolBadge = schoolBadge;
    }

    @XmlElement
    public String getSchoolMotto()
    {
        return schoolMotto;
    }

    public void setSchoolMotto(String schoolMotto)
    {
        this.schoolMotto = schoolMotto;
    }

    @XmlElement
    public String getSchoolSong()
    {
        return schoolSong;
    }

    public void setSchoolSong(String schoolSong)
    {
        this.schoolSong = schoolSong;
    }
    
    
}
