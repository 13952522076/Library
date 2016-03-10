package com.sammyun.controller.api.bean.app;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.app.App.OperatingSystem;

@XmlRootElement(name = "appUserBean")
public class AppUserBean
{
    /** 用户id */
    private Long memberId;
    
    /**分页信息*/
    private PageModel page;
    
	/** 应用id*/
	private Long appId;
	
	/** 设备信息*/
	private String deviceInfo;
	
	/** 应用操作系统*/
	private OperatingSystem operatingSystem;

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
    public PageModel getPage()
    {
        return page;
    }

    public void setPage(PageModel page)
    {
        this.page = page;
    }
    
    @XmlElement
	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	@XmlElement
	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	@XmlElement
	public OperatingSystem getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(OperatingSystem operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
}
