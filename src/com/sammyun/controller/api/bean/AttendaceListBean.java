package com.sammyun.controller.api.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.Member.MemberType;

@XmlRootElement(name = "attendaceDetailBean")
public class AttendaceListBean
{
    /** 查询时间 */
    private String date;

    /** 班级id */
    private Long dictClassId;

    /** 老师id */
    private Long memberId;

    /** 学号 */
    private String studentNo;

    /** 角色属性 */
    private MemberType memberType;
    
    /**分页信息*/
    private PageModel page;

    @XmlElement
    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    @XmlElement
    public Long getDictClassId()
    {
        return dictClassId;
    }

    public void setDictClassId(Long dictClassId)
    {
        this.dictClassId = dictClassId;
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
    public String getStudentNo()
    {
        return studentNo;
    }

    public void setStudentNo(String studentNo)
    {
        this.studentNo = studentNo;
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
    public PageModel getPage()
    {
        return page;
    }

    public void setPage(PageModel page)
    {
        this.page = page;
    }

}
