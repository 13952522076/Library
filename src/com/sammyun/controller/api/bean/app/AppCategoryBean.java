package com.sammyun.controller.api.bean.app;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.controller.api.model.PageModel;

@XmlRootElement(name = "appCategoryBean")
public class AppCategoryBean {
	
    /**分页信息*/
    private PageModel page;
    
    /** 应用分类id*/
    private Long appCategoryId;
    
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
	public Long getAppCategoryId() {
		return appCategoryId;
	}

	public void setAppCategoryId(Long appCategoryId) {
		this.appCategoryId = appCategoryId;
	}
    
}
