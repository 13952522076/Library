/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao;

import com.sammyun.entity.Sn.Type;

/**
 * Dao - 序列号
 * 
 * @author Sencloud Team

 */
public interface SnDao
{

    /**
     * 生成序列号
     * 
     * @param type 类型
     * @return 序列号
     */
    String generate(Type type);

}
