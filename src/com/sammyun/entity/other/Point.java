package com.sammyun.entity.other;

// Generated 2015-3-21 21:51:07 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sammyun.entity.BaseEntity;

/**
 * Point * Entity - 用户积分
 * 


 */
@Entity
@Table(name = "t_pe_point")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_point_sequence")
public class Point extends BaseEntity {

	private BigDecimal id;
	private Long bizId;
	private Date createAt;
	private Date expireAt;
	private String memo;
	private String owner;
	private Long status;
	private Long type;
	private BigDecimal userId;
	private Long valueP;

	public Point() {
	}

	public Point(BigDecimal id, Date createAt, Date expireAt) {
		this.id = id;
		this.createAt = createAt;
		this.expireAt = expireAt;
	}

	public Point(BigDecimal id, Long bizId, Date createAt, Date expireAt,
			String memo, String owner, Long status, Long type,
			BigDecimal userId, Long valueP) {
		this.id = id;
		this.bizId = bizId;
		this.createAt = createAt;
		this.expireAt = expireAt;
		this.memo = memo;
		this.owner = owner;
		this.status = status;
		this.type = type;
		this.userId = userId;
		this.valueP = valueP;
	}

	@Column(name = "biz_id", precision = 10, scale = 0)
	public Long getBizId() {
		return this.bizId;
	}

	public void setBizId(Long bizId) {
		this.bizId = bizId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at", nullable = false, length = 19)
	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expire_at", nullable = false, length = 19)
	public Date getExpireAt() {
		return this.expireAt;
	}

	public void setExpireAt(Date expireAt) {
		this.expireAt = expireAt;
	}

	@Column(name = "memo", length = 1000)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "owner", length = 64)
	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Column(name = "status", precision = 10, scale = 0)
	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name = "type", precision = 10, scale = 0)
	public Long getType() {
		return this.type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	@Column(name = "user_id", scale = 0)
	public BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

	@Column(name = "value_p", precision = 10, scale = 0)
	public Long getValueP() {
		return this.valueP;
	}

	public void setValueP(Long valueP) {
		this.valueP = valueP;
	}

}
