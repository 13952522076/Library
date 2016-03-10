package com.sammyun.controller.api.block.app;

import javax.xml.bind.annotation.XmlElement;

import com.sammyun.entity.app.AppClientVersion.OperatingSystem;
import com.sammyun.entity.app.AppClientVersion.TypeOfUpgrade;

public class AppClientVersionBlock {
	
	/** 显示名称 */
    private String versionName;

    /** 终端应用版本ID */
    private String appId;

    /** 升级消息 */
    private String description;

    /** 下载地址 */
    private String urlDownload;

    /** 文件大小 */
    private String fileSize;

    /** 版本号 */
    private String versionNumber;

    /** 升级消息是否做了推送 */
    private Boolean flagPublish;

    /** 操作系统 */
    private OperatingSystem operatingSystem;

    /** 升级类型 */
    private TypeOfUpgrade typeOfUpgrade;

    @XmlElement
	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	@XmlElement
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@XmlElement
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement
	public String getUrlDownload() {
		return urlDownload;
	}

	public void setUrlDownload(String urlDownload) {
		this.urlDownload = urlDownload;
	}

	@XmlElement
	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	@XmlElement
	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	@XmlElement
	public Boolean getFlagPublish() {
		return flagPublish;
	}

	public void setFlagPublish(Boolean flagPublish) {
		this.flagPublish = flagPublish;
	}

	@XmlElement
	public OperatingSystem getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(OperatingSystem operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	@XmlElement
	public TypeOfUpgrade getTypeOfUpgrade() {
		return typeOfUpgrade;
	}

	public void setTypeOfUpgrade(TypeOfUpgrade typeOfUpgrade) {
		this.typeOfUpgrade = typeOfUpgrade;
	}
}
