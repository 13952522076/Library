package com.sammyun.service.impl.ad;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.ad.AdNetWorkDao;
import com.sammyun.entity.ad.AdNetWork;
import com.sammyun.service.ad.AdNetWorkService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 *  ServiceImpl - 定向运营商
 * 
 * @author chenyunfeng
 * @version [版本号, Aug 28, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("adNetWorkServiceImpl")
public class AdNetWorkServiceImpl extends BaseServiceImpl<AdNetWork, Long> implements AdNetWorkService
{
    @Resource(name = "adNetWorkDaoImpl")
    private AdNetWorkDao adNetWorkDao;

    @Resource(name = "adNetWorkDaoImpl")
    public void setBaseDao(AdNetWorkDao adNetWorkDao){
        super.setBaseDao(adNetWorkDao);
    }

    @Override
    public List<AdNetWork> finListByNetWork(String netWork)
    {
        return adNetWorkDao.finListByNetWork(netWork);
    }
}
