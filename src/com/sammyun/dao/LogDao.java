/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao;

import com.sammyun.entity.Log;

/**
 * Dao - 日志
 * 
 * @author Sencloud Team

 */
public interface LogDao extends BaseDao<Log, Long>
{

    /**
     * 删除所有日志
     */
    void removeAll();

}
