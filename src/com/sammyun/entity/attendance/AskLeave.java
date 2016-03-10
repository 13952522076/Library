package com.sammyun.entity.attendance;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.dict.DictStudent;

/**
 * Entity - 学生请假
 * 
 * @author xutianlong
 * @version [版本号, Apr 11, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_ask_leave")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_ask_leave_sequence")
public class AskLeave extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    public enum AskLeaveType
    {
        /** 病假 */
        sick,
        /** 事假 */
        compassionate
    }

    /** 请假开始时间 */
    public Date leaveStartDate;

    /** 请假结束时间 */
    public Date leaveEndDate;

    /** 请假学生名称 */
    public String stuName;

    /** 请假学生编号 */
    public String stuNo;

    /** 请假说明 */
    public String description;

    /** 请假学生 */
    public DictStudent dictStudent;

    /** 是否同意 */
    public Boolean isAgree;

    /** 审批用户ID */
    public String auditingUserId;

    /** 审批人 */
    public String auditingUserName;
    
    /** 请假类型 */
    public  AskLeaveType askLeaveType;

    /**
     * @return 返回 leaveStartDate
     */
    public Date getLeaveStartDate()
    {
        return leaveStartDate;
    }

    /**
     * @param 对leaveStartDate进行赋值
     */
    public void setLeaveStartDate(Date leaveStartDate)
    {
        this.leaveStartDate = leaveStartDate;
    }

    /**
     * @return 返回 leaveEndDate
     */
    public Date getLeaveEndDate()
    {
        return leaveEndDate;
    }

    /**
     * @param 对leaveEndDate进行赋值
     */
    public void setLeaveEndDate(Date leaveEndDate)
    {
        this.leaveEndDate = leaveEndDate;
    }

    /**
     * @return 返回 stuName
     */
    public String getStuName()
    {
        return stuName;
    }

    /**
     * @param 对stuName进行赋值
     */
    public void setStuName(String stuName)
    {
        this.stuName = stuName;
    }

    /**
     * @return 返回 stuNo
     */
    public String getStuNo()
    {
        return stuNo;
    }

    /**
     * @param 对stuNo进行赋值
     */
    public void setStuNo(String stuNo)
    {
        this.stuNo = stuNo;
    }

    /**
     * @return 返回 description
     */

    public String getDescription()
    {
        return description;
    }

    /**
     * @param 对description进行赋值
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
    
   

    /**
     * @return 返回 dictStudent
     */
    @NotNull
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public DictStudent getDictStudent()
    {
        return dictStudent;
    }

    /**
     * @param 对dictStudent进行赋值
     */
    public void setDictStudent(DictStudent dictStudent)
    {
        this.dictStudent = dictStudent;
    }

    /**
     * @return 返回 isAgree
     */
    public Boolean getIsAgree()
    {
        return isAgree;
    }

    /**
     * @param 对isAgree进行赋值
     */
    public void setIsAgree(Boolean isAgree)
    {
        this.isAgree = isAgree;
    }

    /**
     * @return 返回 auditingUserId
     */
    public String getAuditingUserId()
    {
        return auditingUserId;
    }

    /**
     * @param 对auditingUserId进行赋值
     */
    public void setAuditingUserId(String auditingUserId)
    {
        this.auditingUserId = auditingUserId;
    }

    /**
     * @return 返回 auditingUserName
     */
    public String getAuditingUserName()
    {
        return auditingUserName;
    }

    /**
     * @param 对auditingUserName进行赋值
     */
    public void setAuditingUserName(String auditingUserName)
    {
        this.auditingUserName = auditingUserName;
    }

    /**
     * @return askLeaveType
     */
    public AskLeaveType getAskLeaveType()
    {
        return askLeaveType;
    }

    /**
     * @param 对askLeaveType进行赋值
     */
    public void setAskLeaveType(AskLeaveType askLeaveType)
    {
        this.askLeaveType = askLeaveType;
    }

    
    
}
