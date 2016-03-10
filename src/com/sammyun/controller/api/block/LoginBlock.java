package com.sammyun.controller.api.block;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.entity.Member.MemberType;

@XmlRootElement(name = "loginBlock")
public class LoginBlock
{
    /** 角色属性 */
    private MemberType memberType;

    /** 真实姓名 */
    private String realName;

    /** 用户id */
    private Long memberId;

    /** 所属学校ID */
    private Long dictSchoolId;
    
    /** 学校名称 */
    private String schoolName;
    
    /** 学校logo */
    private String schoolLogo;
    
    /** 学校背景色 */
    private String bgColor;
    
    /** 学校背景色 */
    private String iconPhoto;
    
    /** 卡号管理 -- 只有老师才有*/
    private String cardNumber;
    
    /**学生基本信息*/
    private List<Login4StudentInfoBlock> studentInfos;

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
    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
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
    public List<Login4StudentInfoBlock> getStudentInfos()
    {
        return studentInfos;
    }

    public void setStudentInfos(List<Login4StudentInfoBlock> studentInfos)
    {
        this.studentInfos = studentInfos;
    }

    @XmlElement
    public Long getDictSchoolId()
    {
        return dictSchoolId;
    }

    public void setDictSchoolId(Long dictSchoolId)
    {
        this.dictSchoolId = dictSchoolId;
    }

    @XmlElement
    public String getSchoolName()
    {
        return schoolName;
    }

    public void setSchoolName(String schoolName)
    {
        this.schoolName = schoolName;
    }

    @XmlElement
    public String getSchoolLogo()
    {
        return schoolLogo;
    }

    public void setSchoolLogo(String schoolLogo)
    {
        this.schoolLogo = schoolLogo;
    }

    @XmlElement
    public String getBgColor()
    {
        return bgColor;
    }

    public void setBgColor(String bgColor)
    {
        this.bgColor = bgColor;
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
    public String getCardNumber()
    {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }
}
