package com.sammyun.controller.api.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.dict.DictClass.ClassStatus;

@XmlRootElement(name = "curriculumScheduleClassInfoBean")
public class CurriculumScheduleClassInfoBean
{
    /** 学校id */
    private Long dictSchoolId;

    /** 老师id */
    private Long memberId;

    /** 角色属性 */
    private MemberType memberType;

    /** 班级状态 */
    private ClassStatus classStatus;

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
    public Long getMemberId()
    {
        return memberId;
    }

    public void setMemberId(Long memberId)
    {
        this.memberId = memberId;
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
    public ClassStatus getClassStatus()
    {
        return classStatus;
    }

    public void setClassStatus(ClassStatus classStatus)
    {
        this.classStatus = classStatus;
    }

}
