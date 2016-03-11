/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sammyun.dao.PluginConfigDao;
import com.sammyun.entity.PluginConfig;
import com.sammyun.service.PluginConfigService;

/**
 * Service - 插件配置
 * 


 */
@Service("pluginConfigServiceImpl")
public class PluginConfigServiceImpl extends BaseServiceImpl<PluginConfig, Long> implements PluginConfigService {

	@Resource(name = "pluginConfigDaoImpl")
	private PluginConfigDao pluginConfigDao;

	@Resource(name = "pluginConfigDaoImpl")
	public void setBaseDao(PluginConfigDao pluginConfigDao) {
		super.setBaseDao(pluginConfigDao);
	}

	@Transactional(readOnly = true)
	public boolean pluginIdExists(String pluginId) {
		return pluginConfigDao.pluginIdExists(pluginId);
	}

	@Transactional(readOnly = true)
	public PluginConfig findByPluginId(String pluginId) {
		return pluginConfigDao.findByPluginId(pluginId);
	}

}