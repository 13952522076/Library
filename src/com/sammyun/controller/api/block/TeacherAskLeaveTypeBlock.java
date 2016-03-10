package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.entity.attendance.TeacherAskLeave.AskLeaveType;

@XmlRootElement(name = "teacherAskLeaveTypeBlock")
public class TeacherAskLeaveTypeBlock
{
    /**类型名称*/
    private String typeName;
    
    /** 类型*/
    private AskLeaveType askLeaveType;

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
    public AskLeaveType getAskLeaveType()
    {
        return askLeaveType;
    }


    public void setAskLeaveType(AskLeaveType askLeaveType)
    {
        this.askLeaveType = askLeaveType;
    }
    
    
}
