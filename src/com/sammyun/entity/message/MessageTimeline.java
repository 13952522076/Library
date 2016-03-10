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
 * MessageTimeline * Entity - 消息时间线
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Entity
@Table(name = "t_pe_message_timeline")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_message_timeline_sequence")
public class MessageTimeline extends BaseEntity
{

    private Date timeline;

    private BigDecimal userId;

    private BigDecimal vgroupId;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timeline", nullable = false, length = 19)
    public Date getTimeline()
    {
        return this.timeline;
    }

    public void setTimeline(Date timeline)
    {
        this.timeline = timeline;
    }

    @Column(name = "user_id", scale = 0)
    public BigDecimal getUserId()
    {
        return this.userId;
    }

    public void setUserId(BigDecimal userId)
    {
        this.userId = userId;
    }

    @Column(name = "vgroup_id", scale = 0)
    public BigDecimal getVgroupId()
    {
        return this.vgroupId;
    }

    public void setVgroupId(BigDecimal vgroupId)
    {
        this.vgroupId = vgroupId;
    }

}
