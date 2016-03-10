package com.sammyun.controller.api.block.app;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "appBlock")
public class AppBlock
{
    /** 应用id */
    private Long id;

    /** 应用分类 */
    private AppCategoryBlock appCategoryBlock;

    /** 应用名称 */
    private String appName;

    /** 应用描述 */
    private String appDescription;

    /** 应用升级说明 */
    private String appUpDescription;

    /** 应用截图 */
    private List<AppScreenshotBlock> appScreenshotsBlock = new ArrayList<AppScreenshotBlock>();

    /** 应用下载地址 */
    private String installUrl;

    /** 应用图标url */
    private String logoAppImg;

    /** 打开应用的地址 */
    private String openUrl;

    /** 应用大小 */
    private String appSize;

    /** 应用版本号 */
    private String versionCode;

    /** 应用版本名称 */
    private String versionName;

    /** 应用标识 */
    private String appCode;

    /** 开发者 */
    private String developer;
    
    /** 应用统计*/
    private AppStatBlock appStatBlock;
    
    /** 应用附件地址*/
    private String appAttachment;
    
    /** 应用系统*/
    private String operatingSystem;

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
    public AppCategoryBlock getAppCategoryBlock()
    {
        return appCategoryBlock;
    }

    public void setAppCategoryBlock(AppCategoryBlock appCategoryBlock)
    {
        this.appCategoryBlock = appCategoryBlock;
    }

    @XmlElement
    public String getAppName()
    {
        return appName;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    @XmlElement
    public String getAppDescription()
    {
        return appDescription;
    }

    public void setAppDescription(String appDescription)
    {
        this.appDescription = appDescription;
    }

    @XmlElement
    public String getAppUpDescription()
    {
        return appUpDescription;
    }

    public void setAppUpDescription(String appUpDescription)
    {
        this.appUpDescription = appUpDescription;
    }

    @XmlElement
    public List<AppScreenshotBlock> getAppScreenshotsBlock()
    {
        return appScreenshotsBlock;
    }

    public void setAppScreenshotsBlock(List<AppScreenshotBlock> appScreenshotsBlock)
    {
        this.appScreenshotsBlock = appScreenshotsBlock;
    }

    @XmlElement
    public String getInstallUrl()
    {
        return installUrl;
    }

    public void setInstallUrl(String installUrl)
    {
        this.installUrl = installUrl;
    }

    @XmlElement
    public String getLogoAppImg()
    {
        return logoAppImg;
    }

    public void setLogoAppImg(String logoAppImg)
    {
        this.logoAppImg = logoAppImg;
    }

    @XmlElement
    public String getOpenUrl()
    {
        return openUrl;
    }

    public void setOpenUrl(String openUrl)
    {
        this.openUrl = openUrl;
    }

    @XmlElement
    public String getAppSize()
    {
        return appSize;
    }

    public void setAppSize(String appSize)
    {
        this.appSize = appSize;
    }

    @XmlElement
    public String getVersionCode()
    {
        return versionCode;
    }

    public void setVersionCode(String versionCode)
    {
        this.versionCode = versionCode;
    }

    @XmlElement
    public String getVersionName()
    {
        return versionName;
    }

    public void setVersionName(String versionName)
    {
        this.versionName = versionName;
    }

    @XmlElement
    public String getAppCode()
    {
        return appCode;
    }

    public void setAppCode(String appCode)
    {
        this.appCode = appCode;
    }

    @XmlElement
    public String getDeveloper()
    {
        return developer;
    }

    public void setDeveloper(String developer)
    {
        this.developer = developer;
    }

    @XmlElement
	public AppStatBlock getAppStatBlock() {
		return appStatBlock;
	}

	public void setAppStatBlock(AppStatBlock appStatBlock) {
		this.appStatBlock = appStatBlock;
	}

	@XmlElement
	public String getAppAttachment() {
		return appAttachment;
	}

	public void setAppAttachment(String appAttachment) {
		this.appAttachment = appAttachment;
	}

	@XmlElement
	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

}
