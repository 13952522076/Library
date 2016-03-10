/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao;

import com.sammyun.entity.PluginConfig;

/**
 * Dao - 插件配置
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface PluginConfigDao extends BaseDao<PluginConfig, Long>
{

    /**
     * 判断插件ID是否存在
     * 
     * @param pluginId 插件ID
     * @return 插件ID是否存在
     */
    boolean pluginIdExists(String pluginId);

    /**
     * 根据插件ID查找插件配置
     * 
     * @param pluginId 插件ID
     * @return 插件配置，若不存在则返回null
     */
    PluginConfig findByPluginId(String pluginId);

}
