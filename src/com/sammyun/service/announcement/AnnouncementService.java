/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service.announcement;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.entity.announcement.Announcement;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.BaseService;

/**
 * Service - 通知公告数据
 * 


 */
public interface AnnouncementService extends BaseService<Announcement, Long>
{

    /**
     * 根据学校查询通知公告
     */
    public Page<Announcement> findBySchool(DictSchool dictSchool,Integer status,Pageable pageable);
}
