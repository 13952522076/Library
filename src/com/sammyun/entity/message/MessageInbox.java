package com.sammyun.entity.message;

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
 * Entity - 站内信(系统消息)
 * 


 */
@Entity
@Table(name = "t_pe_message_inbox")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_message_inbox_sequence")
public class MessageInbox extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 2359522204202312217L;

    private Date readAt;

    private BigDecimal receiverId;

    private Date sendAt;

    private Long status;

    private BigDecimal messageId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "read_at", nullable = false, length = 19)
    public Date getReadAt()
    {
        return this.readAt;
    }

    public void setReadAt(Date readAt)
    {
        this.readAt = readAt;
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

    @Column(name = "status", precision = 10, scale = 0)
    public Long getStatus()
    {
        return this.status;
    }

    public void setStatus(Long status)
    {
        this.status = status;
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
