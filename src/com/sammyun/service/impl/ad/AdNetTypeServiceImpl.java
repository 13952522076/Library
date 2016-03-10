package com.sammyun.service.impl.ad;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.ad.AdNetTypeDao;
import com.sammyun.entity.ad.AdNetType;
import com.sammyun.service.ad.AdNetTypeService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 *  ServiceImpl - 定向网络类型
 * 
 * @author chenyunfeng
 * @version [版本号, Aug 28, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("adNetTypeServiceImpl")
public class AdNetTypeServiceImpl extends BaseServiceImpl<AdNetType, Long> implements AdNetTypeService
{
    @Resource(name = "adNetTypeDaoImpl")
    private AdNetTypeDao adNetTypeDao;

    @Resource(name = "adNetTypeDaoImpl")
    public void setBaseDao(AdNetTypeDao adNetTypeDao){
        super.setBaseDao(adNetTypeDao);
    }

    @Override
    public List<AdNetType> finListByNetType(String netType)
    {
        return adNetTypeDao.finListByNetType(netType);
    }
}
