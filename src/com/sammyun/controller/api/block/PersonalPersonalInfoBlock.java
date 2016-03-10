package com.sammyun.controller.api.block;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "profilePersonalInfoBlock")
public class PersonalPersonalInfoBlock
{
    /** 真实姓名 */
    private String realName;
    
    /** 用户头像 */
    private String iconPhoto;
    
    /** 手机（用作登陆账号） */
    private String mobile;
    
    /** 省份 */
    private PersonalProvinceBlock province;
    
    /** 城市 */
    private PersonalProvinceBlock city;
    
    /** 地区 */
    private PersonalProvinceBlock region;
    
    private List<PersonalDictStudentBlock> dictStudents;

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
    public String getIconPhoto()
    {
        return iconPhoto;
    }

    public void setIconPhoto(String iconPhoto)
    {
        this.iconPhoto = iconPhoto;
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
    public PersonalProvinceBlock getProvince()
    {
        return province;
    }

    public void setProvince(PersonalProvinceBlock province)
    {
        this.province = province;
    }

    @XmlElement
    public PersonalProvinceBlock getCity()
    {
        return city;
    }

    public void setCity(PersonalProvinceBlock city)
    {
        this.city = city;
    }

    @XmlElement
    public PersonalProvinceBlock getRegion()
    {
        return region;
    }

    public void setRegion(PersonalProvinceBlock region)
    {
        this.region = region;
    }

    @XmlElement
    public List<PersonalDictStudentBlock> getDictStudents()
    {
        return dictStudents;
    }

    public void setDictStudents(List<PersonalDictStudentBlock> dictStudents)
    {
        this.dictStudents = dictStudents;
    }

    
}
