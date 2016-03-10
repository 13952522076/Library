package com.sammyun.controller.api.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.entity.dict.DictStudent.Gender;
import com.sammyun.entity.dict.PatriarchStudentMap.Type;

@XmlRootElement(name = "profileResetDictStudentBean")
public class PersonalResetDictStudentBean
{
    /** 学生id */
    private Long studentId;

    /** 学生姓名 */
    private String studentName;

    /** 性别 */
    private Gender gender;

    /** 生日（YYYY-MM-dd） */
    private String birthday;
    
    /** 是爸爸还是妈妈 */
    private Type type;
    
    /** 修改类型 */
    private String modifyType;
    
    /** 学生家长列表 */
    private Long memberId;
    
    /** 头像修改 */
    private String iconPhoto;

    @XmlElement
    public Long getStudentId()
    {
        return studentId;
    }

    public void setStudentId(Long studentId)
    {
        this.studentId = studentId;
    }

    @XmlElement
    public String getStudentName()
    {
        return studentName;
    }

    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    @XmlElement
    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    @XmlElement
    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    @XmlElement
    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    @XmlElement
    public String getModifyType()
    {
        return modifyType;
    }

    public void setModifyType(String modifyType)
    {
        this.modifyType = modifyType;
    }
    
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

    
}
