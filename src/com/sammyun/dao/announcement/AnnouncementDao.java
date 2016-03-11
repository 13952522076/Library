/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao.announcement;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.BaseDao;
import com.sammyun.entity.announcement.Announcement;
import com.sammyun.entity.dict.DictSchool;

/**
 * Dao - 通知公告数据
 * 


 */
public interface AnnouncementDao extends BaseDao<Announcement, Long>
{
    /**
     * 根据学校查询通知公告
     */
    public Page<Announcement> findBySchool(DictSchool dictSchool,Integer status,Pageable pageable);
}
