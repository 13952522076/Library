package com.sammyun.controller.api.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "attendaceAddListBean")
public class AttendaceAddListBean
{
    /** 考勤设备 */
    private List<AttendaceAddBean> attendanceInfos;

    @XmlElement
    public List<AttendaceAddBean> getAttendanceInfos()
    {
        return attendanceInfos;
    }

    public void setAttendanceInfos(List<AttendaceAddBean> attendanceInfos)
    {
        this.attendanceInfos = attendanceInfos;
    }
}
