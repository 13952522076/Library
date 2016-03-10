package com.sammyun.entity.bus;

// Generated 2015-3-21 21:51:07 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;

/**
 * BusLineCollect * Entity - 线路收藏数据
 * 
 * @author Sencloud Team

 */
@Entity
@Table(name = "t_pe_bus_line_collect")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_bus_line_collect_sequence")
public class BusLineCollect extends BaseEntity
{

    private BigDecimal id;

    private BigDecimal collectTime;

    private BigDecimal collectorId;

    private BigDecimal lineId;

    public BusLineCollect()
    {
    }

    public BusLineCollect(BigDecimal id)
    {
        this.id = id;
    }

    public BusLineCollect(BigDecimal id, BigDecimal collectTime, BigDecimal collectorId, BigDecimal lineId)
    {
        this.id = id;
        this.collectTime = collectTime;
        this.collectorId = collectorId;
        this.lineId = lineId;
    }

    @Column(name = "collect_time", scale = 0)
    public BigDecimal getCollectTime()
    {
        return this.collectTime;
    }

    public void setCollectTime(BigDecimal collectTime)
    {
        this.collectTime = collectTime;
    }

    @Column(name = "collector_id", scale = 0)
    public BigDecimal getCollectorId()
    {
        return this.collectorId;
    }

    public void setCollectorId(BigDecimal collectorId)
    {
        this.collectorId = collectorId;
    }

    @Column(name = "line_id", scale = 0)
    public BigDecimal getLineId()
    {
        return this.lineId;
    }

    public void setLineId(BigDecimal lineId)
    {
        this.lineId = lineId;
    }

}
