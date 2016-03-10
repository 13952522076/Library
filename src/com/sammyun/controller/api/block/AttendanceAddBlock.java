package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "attendanceAddBlock")
public class AttendanceAddBlock
{
    /** app端id */
    private Long appId;

    /** 服务端id -- 考勤详情*/
    private Long serverId;

    @XmlElement
    public Long getAppId()
    {
        return appId;
    }

    public void setAppId(Long appId)
    {
        this.appId = appId;
    }

    @XmlElement
    public Long getServerId()
    {
        return serverId;
    }

    public void setServerId(Long serverId)
    {
        this.serverId = serverId;
    }
    
}
