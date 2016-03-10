/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sammyun.dao.AdminDao;
import com.sammyun.entity.Admin;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.listener.InitListener;

/**
 * Dao - 管理员
 * 
 * @author Sencloud Team

 */
@Repository("adminDaoImpl")
public class AdminDaoImpl extends BaseDaoImpl<Admin, Long> implements AdminDao {
	
	/** logger */
    private static final Logger logger = LoggerFactory.getLogger(InitListener.class);
    
	public boolean usernameExists(String username) {
		if (username == null) {
			return false;
		}
		String jpql = "select count(*) from Admin admin where lower(admin.username) = lower(:username)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
		return count > 0;
	}

	public Admin findByUsername(String username) {
		if (username == null) {
			return null;
		}
		try {
			String jpql = "select admin from Admin admin where lower(admin.username) = lower(:username)";
			return entityManager.createQuery(jpql, Admin.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public DictSchool getSchoolByAdmin(Admin admin) {
		// TODO Auto-generated method stub
		if (admin == null) {
			logger.info("AdminDaoImpl getSchoolByAdmin: "+"admin is null");
			return null;
		}
		long adminId = admin.getId();
		try {
			String jpql = "select dictSchool from Admin admin where admin.id = :adminId";
			return entityManager.createQuery(jpql, DictSchool.class).setFlushMode(FlushModeType.COMMIT).setParameter("adminId", adminId).getSingleResult();
		} catch (NoResultException e) {
			logger.error("AdminDaoImpl getSchoolByAdmin: "+e.getMessage()); 
			return null;
		}
	}

}