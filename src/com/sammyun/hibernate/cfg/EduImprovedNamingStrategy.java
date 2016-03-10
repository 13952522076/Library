/*
 * Copyright 2012-2014 sencloud.com.cn. All rights reserved.
 * Support: http://www.sencloud.com.cn
 * License: http://www.sencloud.com.cn/license
 */
package com.sammyun.hibernate.cfg;

import java.io.Serializable;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.cfg.NamingStrategy;

/**
 * ImprovedNamingStrategy－ 重写hibernate映射规则
 * 
 * @author xutianlong

 */
public class EduImprovedNamingStrategy extends ImprovedNamingStrategy implements NamingStrategy, Serializable
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 5498540925888070785L;

    @Override
    public String columnName(String columnName)
    {
        return addUnderscores(columnName).toUpperCase();
    }

    @Override
    public String tableName(String tableName)
    {
        return addUnderscores(tableName).toUpperCase();
    }
}
