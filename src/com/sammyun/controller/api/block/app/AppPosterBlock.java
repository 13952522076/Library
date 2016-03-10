package com.sammyun.controller.api.block.app;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "appPosterBlock")
public class AppPosterBlock {

	 /** 海报id */
    private Long id;

	/** 海报标题*/
	private String posterName;
	
	/** 海报图片*/
	private String posterImg;

	@XmlElement
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@XmlElement
	public String getPosterName() {
		return posterName;
	}

	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}

	@XmlElement
	public String getPosterImg() {
		return posterImg;
	}

	public void setPosterImg(String posterImg) {
		this.posterImg = posterImg;
	}
}
