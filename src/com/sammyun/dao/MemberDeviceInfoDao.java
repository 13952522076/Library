/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao;

import java.util.List;

import com.sammyun.entity.Member;
import com.sammyun.entity.MemberDeviceInfo;

/**
 * Dao - 会员设备信息列表
 * 
 * @author Sencloud Team

 */
public interface MemberDeviceInfoDao extends BaseDao<MemberDeviceInfo, Long>
{

    /**
     * 查询设备信息
     * 
     * @param deviceToken 设备token
     * @param uuid 设备uuid
     * @return 设备信息列表
     */
    List<MemberDeviceInfo> findDevice(String deviceToken, String uuid);
    
    /**
     * 根据会员查询token非空的设备
     * <功能详细描述>
     * @param member
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<MemberDeviceInfo> findDevice(Member member);
}
