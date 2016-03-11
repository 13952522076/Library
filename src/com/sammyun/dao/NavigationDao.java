/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao;

import java.util.List;

import com.sammyun.entity.Navigation;
import com.sammyun.entity.Navigation.Position;

/**
 * Dao - 导航
 * 


 */
public interface NavigationDao extends BaseDao<Navigation, Long>
{

    /**
     * 查找导航
     * 
     * @param position 位置
     * @return 导航
     */
    List<Navigation> findList(Position position);

}
