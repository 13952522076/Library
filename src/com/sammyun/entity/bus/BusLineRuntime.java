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
 * BusLineRuntime * Entity - 班车营运时间数据
 * 


 */
@Entity
@Table(name = "t_pe_bus_line_runtime")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_bus_line_runtime_sequence")
public class BusLineRuntime extends BaseEntity {

	private BigDecimal id;
	private String name;
	private String value;
	private BigDecimal lineId;

	public BusLineRuntime() {
	}

	public BusLineRuntime(BigDecimal id) {
		this.id = id;
	}

	public BusLineRuntime(BigDecimal id, String name, String value,
			BigDecimal lineId) {
		this.id = id;
		this.name = name;
		this.value = value;
		this.lineId = lineId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "value")
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "line_id", scale = 0)
	public BigDecimal getLineId() {
		return this.lineId;
	}

	public void setLineId(BigDecimal lineId) {
		this.lineId = lineId;
	}

}
