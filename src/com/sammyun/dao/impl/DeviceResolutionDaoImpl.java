package com.sammyun.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.DeviceResolutionDao;
import com.sammyun.entity.DeviceResolution;

@Repository("deviceResolutionDaoImpl")
public class DeviceResolutionDaoImpl extends BaseDaoImpl<DeviceResolution, Long> implements DeviceResolutionDao{
	
	@Override
	public DeviceResolution findRoots(String resolution) {
		List<DeviceResolution> deviceResolution = new ArrayList<DeviceResolution>();
		String jpql = "select deviceResolution from DeviceResolution deviceResolution where deviceResolution.resolution = :resolution";
		deviceResolution= entityManager.createQuery(jpql, DeviceResolution.class).setParameter("resolution", resolution).getResultList();
		if(deviceResolution.size() == 0){
			return null;
		}else{
			DeviceResolution desolution = deviceResolution.get(0) ;
			   return desolution;
		}

		 
	}

}
