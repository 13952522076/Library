/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao;

import java.util.List;

import com.sammyun.entity.Area;

/**
 * Dao - 地区
 * 


 */
public interface AreaDao extends BaseDao<Area, Long>
{

    /**
     * 查找顶级地区
     * 
     * @param count 数量
     * @return 顶级地区
     */
    List<Area> findRoots(Integer count);

}
