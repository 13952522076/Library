package com.sammyun.entity.other;

// Generated 2015-3-21 21:51:07 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;

/**
 * Loginlog * Entity - 登录日志
 * 
 * @author Sencloud Team

 */
@Entity
@Table(name = "t_pe_loginlog")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_loginlog_sequence")
public class Loginlog extends BaseEntity {

	private BigDecimal id;
	private BigDecimal timeLogin;
	private BigDecimal timeLogout;
	private BigDecimal idTerminalenvironment;
	private BigDecimal idUser;

	public Loginlog() {
	}

	public Loginlog(BigDecimal id) {
		this.id = id;
	}

	public Loginlog(BigDecimal id, BigDecimal timeLogin,
			BigDecimal timeLogout, BigDecimal idTerminalenvironment,
			BigDecimal idUser) {
		this.id = id;
		this.timeLogin = timeLogin;
		this.timeLogout = timeLogout;
		this.idTerminalenvironment = idTerminalenvironment;
		this.idUser = idUser;
	}
	
	@Column(name = "time_login", scale = 0)
	public BigDecimal getTimeLogin() {
		return this.timeLogin;
	}

	public void setTimeLogin(BigDecimal timeLogin) {
		this.timeLogin = timeLogin;
	}

	@Column(name = "time_logout", scale = 0)
	public BigDecimal getTimeLogout() {
		return this.timeLogout;
	}

	public void setTimeLogout(BigDecimal timeLogout) {
		this.timeLogout = timeLogout;
	}

	@Column(name = "id_terminalenvironment", scale = 0)
	public BigDecimal getIdTerminalenvironment() {
		return this.idTerminalenvironment;
	}

	public void setIdTerminalenvironment(BigDecimal idTerminalenvironment) {
		this.idTerminalenvironment = idTerminalenvironment;
	}

	@Column(name = "id_user", scale = 0)
	public BigDecimal getIdUser() {
		return this.idUser;
	}

	public void setIdUser(BigDecimal idUser) {
		this.idUser = idUser;
	}

}
