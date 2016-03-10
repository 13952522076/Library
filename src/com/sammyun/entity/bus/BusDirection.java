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
 * BusDirection * Entity - 线路方向数据
 * 
 * @author Sencloud Team

 */
@Entity
@Table(name = "t_pe_bus_direction")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_bus_direction_sequence")
public class BusDirection extends BaseEntity {

	private BigDecimal id;
	private String name;
	private Long status;
	private BigDecimal schoolId;

	public BusDirection() {
	}

	public BusDirection(BigDecimal id) {
		this.id = id;
	}

	public BusDirection(BigDecimal id, String name, Long status,
			BigDecimal schoolId) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.schoolId = schoolId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "status", precision = 10, scale = 0)
	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name = "school_id", scale = 0)
	public BigDecimal getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(BigDecimal schoolId) {
		this.schoolId = schoolId;
	}

}
