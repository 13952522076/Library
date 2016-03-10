package com.sammyun.controller.api.block.app;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "appUserBlock")
public class AppUserBlock
{
    /** 用户App清单id*/
    private Long id;
    
    /** 应用block */
    private AppBlock appBlock;

    /** 安装时间 */
    private String installTime;

    /** 升级时间 */
    private String updateTime;

    /** 安装的设备信息 以JSON格式保存 */
    private String deviceInfo;
    
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
    public AppBlock getAppBlock() {
		return appBlock;
	}

	public void setAppBlock(AppBlock appBlock) {
		this.appBlock = appBlock;
	}
    
    @XmlElement
    public String getInstallTime()
    {
        return installTime;
    }
    
	public void setInstallTime(String installTime)
    {
        this.installTime = installTime;
    }

    @XmlElement
    public String getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }

    @XmlElement
    public String getDeviceInfo()
    {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo)
    {
        this.deviceInfo = deviceInfo;
    }

}
