package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.entity.message.Message.MessageCategory;

@XmlRootElement(name = "messageListBlock")
public class MessageListBlock
{
    /** id */
    private Long id;
    
    /** 消息主题 */
    private String subject;

    /** 内容 */
    private String body;

    /** 收件人已读 */
    private Boolean receiverRead;
    
    /** 用户头像 */
    private String iconPhoto;
    
    /** 页面是否跳转 */
    private boolean isAdjust;
    
    /** 真实姓名 */
    private String realName;
    
    /** 创建时间 */
    private String createDate;
    
//    /** 访问或者跳转地址 */
//    private String url;
    
    /** 消息类型 */
    private MessageCategory messageCategory;
    
    /** 跳转id */
    private String transferId;

    @XmlElement
    public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@XmlElement
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

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
    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    @XmlElement
    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    @XmlElement
    public Boolean getReceiverRead()
    {
        return receiverRead;
    }

    public void setReceiverRead(Boolean receiverRead)
    {
        this.receiverRead = receiverRead;
    }

    @XmlElement
    public String getIconPhoto()
    {
        return iconPhoto;
    }

    public void setIconPhoto(String iconPhoto)
    {
        this.iconPhoto = iconPhoto;
    }

    @XmlElement
	public boolean isAdjust() {
		return isAdjust;
	}

	public void setAdjust(boolean isAdjust) {
		this.isAdjust = isAdjust;
	}

//	@XmlElement
//    public String getUrl()
//    {
//        return url;
//    }
//
//    public void setUrl(String url)
//    {
//        this.url = url;
//    }

    @XmlElement
    public MessageCategory getMessageCategory()
    {
        return messageCategory;
    }

    public void setMessageCategory(MessageCategory messageCategory)
    {
        this.messageCategory = messageCategory;
    }

    @XmlElement
    public String getTransferId()
    {
        return transferId;
    }

    public void setTransferId(String transferId)
    {
        this.transferId = transferId;
    }

    
}
