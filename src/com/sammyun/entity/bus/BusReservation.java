package com.sammyun.entity.bus;

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
 * BusReservation * Entity - 预约数据
 */
@Entity
@Table(name = "t_pe_bus_reservation")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_bus_reservation_sequence")
public class BusReservation extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -1491435347995499835L;

    @SuppressWarnings("unused")
    private BigDecimal id;

    private BigDecimal createTime;

    private Date reservatDate;

    private Long status;

    private BigDecimal reservatorId;

    private BigDecimal schoolId;

    private BigDecimal stationId;

    public BusReservation()
    {
    }

    public BusReservation(BigDecimal id)
    {
        this.id = id;
    }

    public BusReservation(BigDecimal id, BigDecimal createTime, Date reservatDate, Long status,
            BigDecimal reservatorId, BigDecimal schoolId, BigDecimal stationId)
    {
        this.id = id;
        this.createTime = createTime;
        this.reservatDate = reservatDate;
        this.status = status;
        this.reservatorId = reservatorId;
        this.schoolId = schoolId;
        this.stationId = stationId;
    }

    @Column(name = "create_time", scale = 0)
    public BigDecimal getCreateTime()
    {
        return this.createTime;
    }

    public void setCreateTime(BigDecimal createTime)
    {
        this.createTime = createTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reservat_date", length = 19)
    public Date getReservatDate()
    {
        return this.reservatDate;
    }

    public void setReservatDate(Date reservatDate)
    {
        this.reservatDate = reservatDate;
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

    @Column(name = "reservator_id", scale = 0)
    public BigDecimal getReservatorId()
    {
        return this.reservatorId;
    }

    public void setReservatorId(BigDecimal reservatorId)
    {
        this.reservatorId = reservatorId;
    }

    @Column(name = "school_id", scale = 0)
    public BigDecimal getSchoolId()
    {
        return this.schoolId;
    }

    public void setSchoolId(BigDecimal schoolId)
    {
        this.schoolId = schoolId;
    }

    @Column(name = "station_id", scale = 0)
    public BigDecimal getStationId()
    {
        return this.stationId;
    }

    public void setStationId(BigDecimal stationId)
    {
        this.stationId = stationId;
    }

}
