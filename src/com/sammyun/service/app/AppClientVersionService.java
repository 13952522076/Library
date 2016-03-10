/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service.app;

import java.util.List;

import com.sammyun.entity.app.AppClientVersion.OperatingSystem;
import com.sammyun.entity.app.AppClientVersion;
import com.sammyun.service.BaseService;

/**
 * Service - 终端版本
 * @author tangchao
 * @version 1.0
 *
 */
public interface AppClientVersionService extends BaseService<AppClientVersion, Long>
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
