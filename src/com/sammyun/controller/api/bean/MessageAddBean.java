package com.sammyun.controller.api.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.entity.message.Message.MessageCategory;

@XmlRootElement(name = "messageAddBean")
public class MessageAddBean
{
    /** 消息类型 */
    private MessageCategory messageCategory;

    /** 消息主题 */
    private String subject;

    /** 内容 */
    private String body;

    /** 发件人 */
    private Long senderId;

    /** 收件人 */
    private Long[] receiverIds;

//    /** 访问或者跳转地址 */
//    private String url;

//    /** ip */
//    private String ip;

    /** 隶属学校 */
    private Long dictSchoolId;

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
    public Long getSenderId()
    {
        return senderId;
    }

    public void setSenderId(Long senderId)
    {
        this.senderId = senderId;
    }

    @XmlElement
    public Long[] getReceiverIds()
    {
        return receiverIds;
    }

    public void setReceiverIds(Long[] receiverIds)
    {
        this.receiverIds = receiverIds;
    }

    @XmlElement
    public Long getDictSchoolId()
    {
        return dictSchoolId;
    }

    public void setDictSchoolId(Long dictSchoolId)
    {
        this.dictSchoolId = dictSchoolId;
    }

}
