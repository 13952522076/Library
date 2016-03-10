package com.sammyun.controller.api.block.app;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "appCategoryBlock")
public class AppCategoryBlock {
	
	/** 应用分类id */
	private Long id;

	/** 应用分类名称 */
    private String name;

    /** 应用分类说明 */
    private String description;
    
    /** 应用分类图标*/
    private String appCategoryLogoUrl;
    
    @XmlElement
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement
	public String getAppCategoryLogoUrl() {
		return appCategoryLogoUrl;
	}

	public void setAppCategoryLogoUrl(String appCategoryLogoUrl) {
		this.appCategoryLogoUrl = appCategoryLogoUrl;
	}
	
}
