package com.sammyun.dao.impl;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.DeviceStartupCoverDao;
import com.sammyun.entity.DeviceStartupCover;

@Repository("deviceStartupCoverDaoImpl")
public class DeviceStartupCoverDaoImpl extends BaseDaoImpl<DeviceStartupCover, Long> implements DeviceStartupCoverDao{

}
