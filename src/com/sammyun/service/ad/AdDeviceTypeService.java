package com.sammyun.service.ad;

import java.util.List;

import com.sammyun.entity.ad.AdDeviceType;
import com.sammyun.service.BaseService;

/**
 *  Service - 定向设备
 * 
 * @author chenyunfeng
 * @version [版本号, Aug 28, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface AdDeviceTypeService extends BaseService<AdDeviceType, Long>
{
    /**
     * 查找定向设备
     * <功能详细描述>
     * @param deviceType
     * @return
     * @see [类、类#方法、类#成员]
     */
   public List<AdDeviceType> finListByDeviceType(String deviceType);
}
