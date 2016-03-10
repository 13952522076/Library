/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service;


/**
 * Service - 搜索
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface SearchService
{

    /**
     * 创建索引
     */
    void index();

    /**
     * 创建索引
     * 
     * @param type 索引类型
     */
    void index(Class<?> type);

    /**
     * 删除索引
     */
    void purge();

    /**
     * 删除索引
     * 
     * @param type 索引类型
     */
    void purge(Class<?> type);

}
