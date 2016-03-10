package com.sammyun.controller.api.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "profileResetPersonalBean")
public class PersonalResetPersonalBean
{
    private Long memberId;

    /** 真实姓名 */
    private String realName;

    /** 地区 */
    private Long areaId;

    /** 手机（用作登陆账号） */
    private String mobile;

    /** 密码 */
    private String password;
    
    /** 原密码 */
    private String oldPassword; 
    
    /** 头像修改 */
    private String iconPhoto;

    /** 类型 */
    private String type;

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
    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    @XmlElement
    public Long getAreaId()
    {
        return areaId;
    }

    public void setAreaId(Long areaId)
    {
        this.areaId = areaId;
    }

    @XmlElement
    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    @XmlElement
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @XmlElement
    public String getOldPassword()
    {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword)
    {
        this.oldPassword = oldPassword;
    }

    @XmlElement
    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
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
    
    
}
