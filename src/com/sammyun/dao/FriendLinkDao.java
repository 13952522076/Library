/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao;

import java.util.List;

import com.sammyun.entity.FriendLink;
import com.sammyun.entity.FriendLink.Type;

/**
 * Dao - 友情链接
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface FriendLinkDao extends BaseDao<FriendLink, Long>
{

    /**
     * 查找友情链接
     * 
     * @param type 类型
     * @return 友情链接
     */
    List<FriendLink> findList(Type type);

}
