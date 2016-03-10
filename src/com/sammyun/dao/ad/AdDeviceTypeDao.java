package com.sammyun.dao.ad;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.ad.AdDeviceType;

/**
 *  Dao - 定向设备
 * 
 * @author chenyunfeng
 * @version [版本号, Aug 28, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface AdDeviceTypeDao extends BaseDao<AdDeviceType, Long>
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
