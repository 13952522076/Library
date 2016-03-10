package com.sammyun.controller.api.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.entity.attendance.AttendanceDetail.Device;

@XmlRootElement(name = "attendaceAddBean")
public class AttendaceAddBean
{
    /** 考勤机端主键 */
    private Long id;
    
    /** 考勤设备 */
    private Device device;

    /** 系统（IC/IOS/安卓等） */
    private String systemInfo;
    
    /** 卡号管理 */
    private String cardNumber;
    
    /** 打卡时间 格式（yyyy-MM-dd hh:mm:ss） */
    private String clockInDate;
    
    /** 入园时间 格式（yyyy-MM-dd hh:mm:ss） */
    private String enterDate;

    /** 出园时间 格式（yyyy-MM-dd hh:mm:ss） */
    private String leaveDate;
    
    /** 标识 -- 是否为实时数据 */
    private boolean hasRealTime;
    
    @XmlElement
    public Device getDevice()
    {
        return device;
    }

    public void setDevice(Device device)
    {
        this.device = device;
    }

    @XmlElement
    public String getSystemInfo()
    {
        return systemInfo;
    }

    public void setSystemInfo(String systemInfo)
    {
        this.systemInfo = systemInfo;
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

    @XmlElement
    public String getClockInDate()
    {
        return clockInDate;
    }

    public void setClockInDate(String clockInDate)
    {
        this.clockInDate = clockInDate;
    }

    @XmlElement
    public String getEnterDate()
    {
        return enterDate;
    }

    public void setEnterDate(String enterDate)
    {
        this.enterDate = enterDate;
    }

    @XmlElement
    public String getLeaveDate()
    {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate)
    {
        this.leaveDate = leaveDate;
    }

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
    public boolean isHasRealTime()
    {
        return hasRealTime;
    }

    public void setHasRealTime(boolean hasRealTime)
    {
        this.hasRealTime = hasRealTime;
    }


}
