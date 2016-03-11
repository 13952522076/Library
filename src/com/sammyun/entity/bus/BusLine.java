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
 * BusLine * Entity - 班车线路数据
 * 


 */
@Entity
@Table(name = "t_pe_bus_line")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_bus_line_sequence")
public class BusLine extends BaseEntity {

	private BigDecimal id;
	private String code;
	private String name;
	private Long status;
	private BigDecimal directionId;
	private BigDecimal schoolId;

	public BusLine() {
	}

	public BusLine(BigDecimal id) {
		this.id = id;
	}

	public BusLine(BigDecimal id, String code, String name, Long status,
			BigDecimal directionId, BigDecimal schoolId) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.status = status;
		this.directionId = directionId;
		this.schoolId = schoolId;
	}

	@Column(name = "code")
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	@Column(name = "direction_id", scale = 0)
	public BigDecimal getDirectionId() {
		return this.directionId;
	}

	public void setDirectionId(BigDecimal directionId) {
		this.directionId = directionId;
	}

	@Column(name = "school_id", scale = 0)
	public BigDecimal getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(BigDecimal schoolId) {
		this.schoolId = schoolId;
	}

}
