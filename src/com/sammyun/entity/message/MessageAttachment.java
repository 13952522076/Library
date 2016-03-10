package com.sammyun.entity.message;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;

/**
 * MessageAttachment * Entity - 消息附件
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Entity
@Table(name = "t_pe_message_attachment")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_message_attachment_sequence")
public class MessageAttachment extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 4925802662720491208L;

    /** 消息体 */
    private Message message;

    /** 消息附件URL */
    private String fileUrl;

    /**
     * @return 返回 message
     */
    public Message getMessage()
    {
        return message;
    }

    /**
     * @param 对message进行赋值
     */
    public void setMessage(Message message)
    {
        this.message = message;
    }

    /**
     * @return 返回 fileUrl
     */
    public String getFileUrl()
    {
        return fileUrl;
    }

    /**
     * @param 对fileUrl进行赋值
     */
    public void setFileUrl(String fileUrl)
    {
        this.fileUrl = fileUrl;
    }
    
}
