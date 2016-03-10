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
 * BusCar * Entity - 车辆数据
 * 
 * @author Sencloud Team

 */
@Entity
@Table(name = "t_pe_bus_car")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_bus_car_sequence")
public class BusCar extends BaseEntity {

	private BigDecimal id;
	private String brand;
	private Long carStatus;
	private Integer rentFlag;
	private BigDecimal iconId;
	private String model;
	private String no;
	private Long seats;
	private Long status;
	private BigDecimal schoolId;

	public BusCar() {
	}

	public BusCar(BigDecimal id) {
		this.id = id;
	}

	public BusCar(BigDecimal id, String brand, Long carStatus,
			Integer rentFlag, BigDecimal iconId, String model, String no,
			Long seats, Long status, BigDecimal schoolId) {
		this.id = id;
		this.brand = brand;
		this.carStatus = carStatus;
		this.rentFlag = rentFlag;
		this.iconId = iconId;
		this.model = model;
		this.no = no;
		this.seats = seats;
		this.status = status;
		this.schoolId = schoolId;
	}

	@Column(name = "brand")
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "car_status", precision = 10, scale = 0)
	public Long getCarStatus() {
		return this.carStatus;
	}

	public void setCarStatus(Long carStatus) {
		this.carStatus = carStatus;
	}

	@Column(name = "rent_flag", precision = 5, scale = 0)
	public Integer getRentFlag() {
		return this.rentFlag;
	}

	public void setRentFlag(Integer rentFlag) {
		this.rentFlag = rentFlag;
	}

	@Column(name = "icon_id", scale = 0)
	public BigDecimal getIconId() {
		return this.iconId;
	}

	public void setIconId(BigDecimal iconId) {
		this.iconId = iconId;
	}

	@Column(name = "model")
	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "no")
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	@Column(name = "seats", precision = 10, scale = 0)
	public Long getSeats() {
		return this.seats;
	}

	public void setSeats(Long seats) {
		this.seats = seats;
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
