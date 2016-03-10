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
 * BusDriver * Entity - 驾驶员数据
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Entity
@Table(name = "t_pe_bus_driver")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_bus_driver_sequence")
public class BusDriver extends BaseEntity {

	private BigDecimal id;
	private String brand;
	private String mobile;
	private String name;
	private Long status;
	private BigDecimal schoolId;
	private BigDecimal userId;

	public BusDriver() {
	}

	public BusDriver(BigDecimal id) {
		this.id = id;
	}

	public BusDriver(BigDecimal id, String brand, String mobile,
			String name, Long status, BigDecimal schoolId, BigDecimal userId) {
		this.id = id;
		this.brand = brand;
		this.mobile = mobile;
		this.name = name;
		this.status = status;
		this.schoolId = schoolId;
		this.userId = userId;
	}
	
	@Column(name = "brand")
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "mobile")
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	@Column(name = "user_id", scale = 0)
	public BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

}
