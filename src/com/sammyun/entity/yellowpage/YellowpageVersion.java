package com.sammyun.entity.yellowpage;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;

/**
 * ProfilesImage * Entity - 概况图片数据
 * 


 */
@Entity
@Table(name = "t_pe_yellowpage_version")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_yellowpage_version_sequence")
public class YellowpageVersion extends BaseEntity{
	private BigDecimal id;
	private BigDecimal version;
	
	public YellowpageVersion(){};
	public YellowpageVersion(BigDecimal id) {
		this.id = id;
	};
	public YellowpageVersion(BigDecimal id,BigDecimal version) {
		this.id = id;
		this.version = version;
	}
	@Column(name = "version")
	public BigDecimal getVersion() {
		return version;
	}
	public void setVersion(BigDecimal version) {
		this.version = version;
	}
	
}
