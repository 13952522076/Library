package com.sammyun.controller.api.bean.app;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.app.AppPoster.OperatingSystem;

@XmlRootElement(name = "appPosterBean")
public class AppPosterBean {

	/** 分页信息*/
	private PageModel page;
	
	/** 应用海报的操作系统*/
	private OperatingSystem operatingSystem;

	@XmlElement
	public PageModel getPage() {
		return page;
	}

	public void setPage(PageModel page) {
		this.page = page;
	}

	@XmlElement
	public OperatingSystem getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(OperatingSystem operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
}
