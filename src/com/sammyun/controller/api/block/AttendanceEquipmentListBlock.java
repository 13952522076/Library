package com.sammyun.controller.api.block;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.entity.Member.MemberType;

@XmlRootElement(name = "attendanceEquipmentListBlock")
public class AttendanceEquipmentListBlock
{
     
    /** 学生id */
    private Long id;
    
    /** 真实姓名 */
    private String realName;
    
    /** 用户头像 */
    private String iconPhoto;
    
    /** 卡号管理 */
    private List<AttendanceEquipmentCardBlock> cardNumbers;
    
    /** 关系名称 */
    private String typeName;
    
    /** 学生姓名 */
    private String studentName;
    
    /** 学生头像 */
    private String studentIconPhoto;
    
    /** 班级名 */
    private String dictClassName;
    
    /** 角色属性 */
    private MemberType memberType;
    
    /** 家庭成员 */
    private List<AttendanceEquipmentListBlock> familyMembers;
    
    /** 更新时间 */
    private String modifyDate;
    
    /** 人员信息 */
    private String json;

    @XmlElement
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
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
    public String getIconPhoto()
    {
        return iconPhoto;
    }

    public void setIconPhoto(String iconPhoto)
    {
        this.iconPhoto = iconPhoto;
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
    public String getStudentName()
    {
        return studentName;
    }

    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    @XmlElement
    public String getStudentIconPhoto()
    {
        return studentIconPhoto;
    }

    public void setStudentIconPhoto(String studentIconPhoto)
    {
        this.studentIconPhoto = studentIconPhoto;
    }

    @XmlElement
    public String getDictClassName()
    {
        return dictClassName;
    }

    public void setDictClassName(String dictClassName)
    {
        this.dictClassName = dictClassName;
    }

    @XmlElement
    public MemberType getMemberType()
    {
        return memberType;
    }

    public void setMemberType(MemberType memberType)
    {
        this.memberType = memberType;
    }

    @XmlElement
    public List<AttendanceEquipmentListBlock> getFamilyMembers()
    {
        return familyMembers;
    }

    public void setFamilyMembers(List<AttendanceEquipmentListBlock> familyMembers)
    {
        this.familyMembers = familyMembers;
    }

    @XmlElement
    public List<AttendanceEquipmentCardBlock> getCardNumbers()
    {
        return cardNumbers;
    }

    public void setCardNumbers(List<AttendanceEquipmentCardBlock> cardNumbers)
    {
        this.cardNumbers = cardNumbers;
    }

    @XmlElement
    public String getModifyDate()
    {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate)
    {
        this.modifyDate = modifyDate;
    }

    @XmlElement
    public String getJson()
    {
        return json;
    }

    public void setJson(String json)
    {
        this.json = json;
    }
    
    
}
