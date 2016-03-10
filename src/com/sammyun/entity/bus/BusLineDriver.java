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
 * BusLineDriver * Entity - 线路车辆司机的关联关系
 * 
 * @author Sencloud Team

 */
@Entity
@Table(name = "t_pe_bus_line_driver")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_bus_line_driver_sequence")
public class BusLineDriver extends BaseEntity {

	private BigDecimal id;
	private BigDecimal carId;
	private BigDecimal driverId;
	private BigDecimal lineId;

	public BusLineDriver() {
	}

	public BusLineDriver(BigDecimal id) {
		this.id = id;
	}

	public BusLineDriver(BigDecimal id, BigDecimal carId,
			BigDecimal driverId, BigDecimal lineId) {
		this.id = id;
		this.carId = carId;
		this.driverId = driverId;
		this.lineId = lineId;
	}

	@Column(name = "car_id", scale = 0)
	public BigDecimal getCarId() {
		return this.carId;
	}

	public void setCarId(BigDecimal carId) {
		this.carId = carId;
	}

	@Column(name = "driver_id", scale = 0)
	public BigDecimal getDriverId() {
		return this.driverId;
	}

	public void setDriverId(BigDecimal driverId) {
		this.driverId = driverId;
	}

	@Column(name = "line_id", scale = 0)
	public BigDecimal getLineId() {
		return this.lineId;
	}

	public void setLineId(BigDecimal lineId) {
		this.lineId = lineId;
	}

}
