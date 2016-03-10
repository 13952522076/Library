/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao.app;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.app.AppClientVersion.OperatingSystem;
import com.sammyun.entity.app.AppClientVersion;

/**
 * Dao - 终端版本
 * 
 * @author tangchao
 * @version 1.0
 */
public interface AppClientVersionDao extends BaseDao<AppClientVersion, Long>
{
	
	/**
	 *  根据应用系统查询终端版本列表
	 * @param operatingSystem
	 * @return
	 */
	List<AppClientVersion> findByOperatingSystem(OperatingSystem operatingSystem);
	
	/**
     * 验证appId唯一
     * @param appId
     * @return
     */
    public Boolean checkAppIdUnique(String appId);
}
