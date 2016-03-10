package com.sammyun.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.DeviceResolutionDao;
import com.sammyun.entity.DeviceResolution;
import com.sammyun.service.DeviceResolutionService;

@Service("deviceResolutionServiceImpl")
public class DeviceResolutionServiceImpl extends BaseServiceImpl<DeviceResolution, Long> implements DeviceResolutionService{
	
	@Resource(name = "deviceResolutionDaoImpl")
	 private DeviceResolutionDao  deviceResolutionDao;
	
	@Resource(name = "deviceResolutionDaoImpl")
	public void setBaseDao(DeviceResolutionDao deviceResolutionDao){
		super.setBaseDao(deviceResolutionDao);
	}

	@Override
	public DeviceResolution findRoots(String resolution) {
		// TODO Auto-generated method stub
		 return deviceResolutionDao.findRoots(resolution);
	}
}
