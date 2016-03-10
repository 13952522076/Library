package com.sammyun.service;

import com.sammyun.entity.DeviceResolution;

public interface DeviceResolutionService extends BaseService<DeviceResolution, Long>
{
    DeviceResolution findRoots(String resolution);
}
