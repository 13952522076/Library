/*
 * Copyright 2012-2014 sencloud.com.cn. All rights reserved.
 * Support: http://www.sencloud.com.cn
 * License: http://www.sencloud.com.cn/license
 */
package com.sammyun.service.impl.app;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.app.AppClientVersionDao;
import com.sammyun.entity.app.AppClientVersion;
import com.sammyun.entity.app.AppClientVersion.OperatingSystem;
import com.sammyun.service.app.AppClientVersionService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * Service - 终端版本
 * 
 * @author tangchao
 * @version 1.0
 */
@Service("appClientVersionServiceImpl")
public class AppClientVersionServiceImpl extends
		BaseServiceImpl<AppClientVersion, Long> implements
		AppClientVersionService {

	@Resource(name = "appClientVersionDaoImpl")
	private AppClientVersionDao appClientVersionDao;

	@Resource(name = "appClientVersionDaoImpl")
	public void setBaseDao(AppClientVersionDao appClientVersionDao) {
		super.setBaseDao(appClientVersionDao);
	}

	@Override
	public List<AppClientVersion> findByOperatingSystem(
			OperatingSystem operatingSystem) {
		return appClientVersionDao.findByOperatingSystem(operatingSystem);
	}
	
	@Override
    public Boolean checkAppIdUnique(String appId){
    	return appClientVersionDao.checkAppIdUnique(appId);
    }
}
