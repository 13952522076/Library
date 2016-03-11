/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.MemberDeviceInfoDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.MemberDeviceInfo;
import com.sammyun.service.MemberDeviceInfoService;

/**
 * Service - 会员设备信息列表
 * 


 */
@Service("memberDeviceInfoServiceImpl")
public class MemberDeviceInfoServiceImpl extends BaseServiceImpl<MemberDeviceInfo, Long> implements MemberDeviceInfoService {

	@Resource(name = "memberDeviceInfoDaoImpl")
	private MemberDeviceInfoDao memberDeviceInfoDao;
	
	@Resource(name = "memberDeviceInfoDaoImpl")
	public void setBaseDao(MemberDeviceInfoDao memberDeviceInfoDao) {
		super.setBaseDao(memberDeviceInfoDao);
	}
	/**
	 * 查询设备信息
	 * 
	 * @param deviceToken
	 *            设备token
	 * @param uuid
	 *             设备uuid      
	 * @return 设备信息列表
	 */
	@Override
	public List<MemberDeviceInfo> findDevice(String deviceToken, String uuid) {
		return memberDeviceInfoDao.findDevice(deviceToken, uuid);
	}
    @Override
    public List<MemberDeviceInfo> findDevice(Member member)
    {
        return memberDeviceInfoDao.findDevice(member);
    }

}
