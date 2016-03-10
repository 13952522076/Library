package com.sammyun.controller.api.bean.app;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.controller.api.model.PageModel;

@XmlRootElement(name = "appReviewBean")
public class AppReviewBean {
	
    /**分页信息*/
    private PageModel page;
    
    /** 应用id*/
    private Long appId;
    
    /** 评分（最高位5颗星（1到5）） */
    private Integer score;

    /** 评论标题 */
    private String title;

    /** 昵称 */
    private String nickName;

    /** 内容 */
    private String content;

    /** IP */
    private String ip;
    
    /** 会员id*/
    private Long memberId;
    
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
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@XmlElement
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
}
