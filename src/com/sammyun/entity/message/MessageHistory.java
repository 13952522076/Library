package com.sammyun.entity.message;

// Generated 2015-3-21 21:51:07 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sammyun.entity.BaseEntity;

/**
 * MessageHistory * Entity - 消息历史
 * 
 * @author Sencloud Team

 */
@Entity
@Table(name = "t_pe_message_history")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_message_history_sequence")
public class MessageHistory extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -4258303707506192980L;

    private Long smsChannel;

    private Date createAt;

    private Date expireAt;

    private String feedback;

    private Boolean ok;

    private Long priority;

    private String receiverContact;

    private BigDecimal receiverId;

    private Boolean retry;

    private Long retryCount;

    private Date sendAt;

    private Long targetOs;

    private BigDecimal messageId;

    @Column(name = "sms_channel", precision = 10, scale = 0)
    public Long getSmsChannel()
    {
        return this.smsChannel;
    }

    public void setSmsChannel(Long smsChannel)
    {
        this.smsChannel = smsChannel;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at", nullable = false, length = 19)
    public Date getCreateAt()
    {
        return this.createAt;
    }

    public void setCreateAt(Date createAt)
    {
        this.createAt = createAt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expire_at", nullable = false, length = 19)
    public Date getExpireAt()
    {
        return this.expireAt;
    }

    public void setExpireAt(Date expireAt)
    {
        this.expireAt = expireAt;
    }

    @Column(name = "feedback", length = 1024)
    public String getFeedback()
    {
        return this.feedback;
    }

    public void setFeedback(String feedback)
    {
        this.feedback = feedback;
    }

    @Column(name = "ok", precision = 1, scale = 0)
    public Boolean getOk()
    {
        return this.ok;
    }

    public void setOk(Boolean ok)
    {
        this.ok = ok;
    }

    @Column(name = "priority", precision = 10, scale = 0)
    public Long getPriority()
    {
        return this.priority;
    }

    public void setPriority(Long priority)
    {
        this.priority = priority;
    }

    @Column(name = "receiver_contact", length = 128)
    public String getReceiverContact()
    {
        return this.receiverContact;
    }

    public void setReceiverContact(String receiverContact)
    {
        this.receiverContact = receiverContact;
    }

    @Column(name = "receiver_id", scale = 0)
    public BigDecimal getReceiverId()
    {
        return this.receiverId;
    }

    public void setReceiverId(BigDecimal receiverId)
    {
        this.receiverId = receiverId;
    }

    @Column(name = "retry", precision = 1, scale = 0)
    public Boolean getRetry()
    {
        return this.retry;
    }

    public void setRetry(Boolean retry)
    {
        this.retry = retry;
    }

    @Column(name = "retry_count", precision = 10, scale = 0)
    public Long getRetryCount()
    {
        return this.retryCount;
    }

    public void setRetryCount(Long retryCount)
    {
        this.retryCount = retryCount;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "send_at", nullable = false, length = 19)
    public Date getSendAt()
    {
        return this.sendAt;
    }

    public void setSendAt(Date sendAt)
    {
        this.sendAt = sendAt;
    }

    @Column(name = "target_os", precision = 10, scale = 0)
    public Long getTargetOs()
    {
        return this.targetOs;
    }

    public void setTargetOs(Long targetOs)
    {
        this.targetOs = targetOs;
    }

    @Column(name = "message_id", scale = 0)
    public BigDecimal getMessageId()
    {
        return this.messageId;
    }

    public void setMessageId(BigDecimal messageId)
    {
        this.messageId = messageId;
    }

}
