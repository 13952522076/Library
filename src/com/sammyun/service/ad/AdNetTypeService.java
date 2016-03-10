package com.sammyun.service.ad;

import java.util.List;

import com.sammyun.entity.ad.AdNetType;
import com.sammyun.service.BaseService;

/**
 *  Service - 定向网络类型
 * 
 * @author chenyunfeng
 * @version [版本号, Aug 28, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface AdNetTypeService extends BaseService<AdNetType, Long>
{
    /**
     * 查找定向网络类型
     * 
     * @param netType
     * @return
     * @see [类、类#方法、类#成员]
     */
   public List<AdNetType> finListByNetType(String netType);
}
