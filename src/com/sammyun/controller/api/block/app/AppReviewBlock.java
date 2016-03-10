package com.sammyun.controller.api.block.app;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "appReviewBlock")
public class AppReviewBlock {
	
	/** 应用评论id*/
	private Long id;
	
	/** 评分（最高位5颗星（1到5）） */
    private Integer score;

    /** 评论标题 */
    private String title;

    /** 昵称 */
    private String nickName;

    /** 内容 */
    private String content;
    
    /** 创建时间*/
    private String createDate;

    @XmlElement
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@XmlElement
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@XmlElement
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@XmlElement
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
