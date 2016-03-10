package com.sammyun.service.impl.ad;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.ad.AdDeviceTypeDao;
import com.sammyun.entity.ad.AdDeviceType;
import com.sammyun.service.ad.AdDeviceTypeService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 *  ServiceImpl - 定向设备
 * 
 * @author chenyunfeng
 * @version [版本号, Aug 28, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("adDeviceTypeServiceImpl")
public class AdDeviceTypeServiceImpl extends BaseServiceImpl<AdDeviceType, Long> implements AdDeviceTypeService
{
    @Resource(name = "adDeviceTypeDaoImpl")
    private AdDeviceTypeDao adDeviceTypeDao;

    @Resource(name = "adDeviceTypeDaoImpl")
    public void setBaseDao(AdDeviceTypeDao adDeviceTypeDao){
        super.setBaseDao(adDeviceTypeDao);
    }

    @Override
    public List<AdDeviceType> finListByDeviceType(String deviceType)
    {
        return adDeviceTypeDao.finListByDeviceType(deviceType);
    }
}
