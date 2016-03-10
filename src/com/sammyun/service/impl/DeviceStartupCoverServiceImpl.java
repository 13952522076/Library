package com.sammyun.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.DeviceStartupCoverDao;
import com.sammyun.entity.DeviceStartupCover;
import com.sammyun.service.DeviceStartupCoverService;

@Service("deviceStartupCoverServiceImpl")
public class DeviceStartupCoverServiceImpl extends BaseServiceImpl<DeviceStartupCover, Long> implements DeviceStartupCoverService{
	
	@Resource(name = "deviceStartupCoverDaoImpl")
	private DeviceStartupCoverDao  deviceStartupCoverDao;
	
	@Resource(name = "deviceStartupCoverDaoImpl")
	public void setBaseDao(DeviceStartupCoverDao deviceStartupCoverDao){
		super.setBaseDao(deviceStartupCoverDao);
	}
}
