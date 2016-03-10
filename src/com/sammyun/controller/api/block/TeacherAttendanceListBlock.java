package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "teacherAttendanceListBlock")
public class TeacherAttendanceListBlock
{
    /** 对应班次名字 */
    private String workSettingName;

    /** 对应的老师名 */
    private String memberName;

    /** 对应的老师头像 */
    private String memberIconPhoto;

    /** 上班 第一次刷卡 */
    private String workSwipeTime;

    /** 下班 第一次刷卡 */
    private String closingSwipeTime;

    /** 迟到情况 */
    private String workStatus;

    /** 早退情况 */
    private String closingStatus;

    @XmlElement
    public String getWorkSettingName()
    {
        return workSettingName;
    }

    public void setWorkSettingName(String workSettingName)
    {
        this.workSettingName = workSettingName;
    }

    @XmlElement
    public String getMemberName()
    {
        return memberName;
    }

    public void setMemberName(String memberName)
    {
        this.memberName = memberName;
    }

    @XmlElement
    public String getMemberIconPhoto()
    {
        return memberIconPhoto;
    }

    public void setMemberIconPhoto(String memberIconPhoto)
    {
        this.memberIconPhoto = memberIconPhoto;
    }

    @XmlElement
    public String getWorkSwipeTime()
    {
        return workSwipeTime;
    }

    public void setWorkSwipeTime(String workSwipeTime)
    {
        this.workSwipeTime = workSwipeTime;
    }

    @XmlElement
    public String getClosingSwipeTime()
    {
        return closingSwipeTime;
    }

    public void setClosingSwipeTime(String closingSwipeTime)
    {
        this.closingSwipeTime = closingSwipeTime;
    }

    @XmlElement
    public String getWorkStatus()
    {
        return workStatus;
    }

    public void setWorkStatus(String workStatus)
    {
        this.workStatus = workStatus;
    }

    @XmlElement
    public String getClosingStatus()
    {
        return closingStatus;
    }

    public void setClosingStatus(String closingStatus)
    {
        this.closingStatus = closingStatus;
    }

    
   
}
