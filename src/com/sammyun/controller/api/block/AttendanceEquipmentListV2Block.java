package com.sammyun.controller.api.block;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "attendanceEquipmentListV2Block")
public class AttendanceEquipmentListV2Block
{
    /** 上学有效时间 开始时间 */
    private String startAttendTime;

    /** 上学有效时间 结束时间 */
    private String endAttendTime;

    /** 放学有效时间 开始时间 */
    private String startFinishTime;
    
    /** 放学有效时间 结束时间 */
    private String endFinishTime;
    
    /** 人员信息 */
    private List<AttendanceEquipmentListBlock> infos;

    @XmlElement
    public String getStartAttendTime()
    {
        return startAttendTime;
    }

    public void setStartAttendTime(String startAttendTime)
    {
        this.startAttendTime = startAttendTime;
    }

    @XmlElement
    public String getEndAttendTime()
    {
        return endAttendTime;
    }

    public void setEndAttendTime(String endAttendTime)
    {
        this.endAttendTime = endAttendTime;
    }

    @XmlElement
    public String getStartFinishTime()
    {
        return startFinishTime;
    }

    public void setStartFinishTime(String startFinishTime)
    {
        this.startFinishTime = startFinishTime;
    }

    @XmlElement
    public String getEndFinishTime()
    {
        return endFinishTime;
    }

    public void setEndFinishTime(String endFinishTime)
    {
        this.endFinishTime = endFinishTime;
    }

    @XmlElement
    public List<AttendanceEquipmentListBlock> getInfos()
    {
        return infos;
    }

    public void setInfos(List<AttendanceEquipmentListBlock> infos)
    {
        this.infos = infos;
    }
    
}
