package com.sammyun.entity.bus;

// Generated 2015-3-21 21:51:07 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;

/**
 * BusHirecar * Entity - 租车预约数据
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_pe_bus_hirecar")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_bus_hirecar_sequence")
public class BusHirecar extends BaseEntity
{

    @SuppressWarnings("unused")
    private BigDecimal id;

    private String tel;

    private BigDecimal schoolId;

    public BusHirecar()
    {
    }

    public BusHirecar(BigDecimal id)
    {
        this.id = id;
    }

    public BusHirecar(BigDecimal id, String tel, BigDecimal schoolId)
    {
        this.id = id;
        this.tel = tel;
        this.schoolId = schoolId;
    }

    @Column(name = "tel")
    public String getTel()
    {
        return this.tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
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

}
