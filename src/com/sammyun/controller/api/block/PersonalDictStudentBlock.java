package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.entity.dict.DictStudent.Gender;
import com.sammyun.entity.dict.PatriarchStudentMap.Type;

@XmlRootElement (name = "profileDictStudentBlock")
public class PersonalDictStudentBlock
{
 
    /** 学生学号 */
    private Long studentId;
    
    /** 学生学号 */
    private String studentNo;

    /** 学生姓名 */
    private String studentName;

    /** 学生头像 */
    private String iconPhoto;

    /** 性别 */
    private Gender gender;
    
    /** 性别 */
    private String genderName;

    /** 生日（YYYY-MM-dd） */
    private String birthday;
    
    /**类型名称*/
    private String typeName;
    
    /** 类型*/
    private Type type;

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
    public String getStudentNo()
    {
        return studentNo;
    }

    public void setStudentNo(String studentNo)
    {
        this.studentNo = studentNo;
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
    public String getIconPhoto()
    {
        return iconPhoto;
    }

    public void setIconPhoto(String iconPhoto)
    {
        this.iconPhoto = iconPhoto;
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
    public String getGenderName()
    {
        return genderName;
    }

    public void setGenderName(String genderName)
    {
        this.genderName = genderName;
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
    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
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
}
