/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao;

import java.util.List;

import com.sammyun.entity.Tag;
import com.sammyun.entity.Tag.Type;

/**
 * Dao - 标签
 * 


 */
public interface TagDao extends BaseDao<Tag, Long>
{

    /**
     * 查找标签
     * 
     * @param type 类型
     * @return 标签
     */
    List<Tag> findList(Type type);

}
