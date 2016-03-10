package com.sammyun.dao;

import com.sammyun.entity.DeviceResolution;

public interface DeviceResolutionDao extends BaseDao<DeviceResolution, Long>
{
    DeviceResolution findRoots(String resolution);
}
