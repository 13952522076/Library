package com.sammyun.controller.api.bean.app;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.app.App.OperatingSystem;

@XmlRootElement(name = "appBean")
public class AppBean
{

    /** 应用所属用户id */
    private Long memberId;

    /** 分页信息 */
    private PageModel page;

    /** 应用分类id */
    private Long appCategoryId;

    /** 手机操作系统 */
    private OperatingSystem operatingSystem;
    
    /** 学校id*/
    private Long schoolId;

    /** 搜索词 */
    private String searchKey;

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
    public Long getAppCategoryId()
    {
        return appCategoryId;
    }

    public void setAppCategoryId(Long appCategoryId)
    {
        this.appCategoryId = appCategoryId;
    }

    @XmlElement
    public String getSearchKey()
    {
        return searchKey;
    }

    public void setSearchKey(String searchKey)
    {
        this.searchKey = searchKey;
    }

    @XmlElement
    public OperatingSystem getOperatingSystem()
    {
        return operatingSystem;
    }

    public void setOperatingSystem(OperatingSystem operatingSystem)
    {
        this.operatingSystem = operatingSystem;
    }

    @XmlElement
	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

}
