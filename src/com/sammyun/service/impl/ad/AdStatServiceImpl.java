package com.sammyun.service.impl.ad;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.ad.AdStatDao;
import com.sammyun.entity.ad.AdStat;
import com.sammyun.service.ad.AdStatService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 *  ServiceImpl - 广告统计 
 * 
 * @author chenyunfeng
 * @version [版本号, Aug 28, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("adStatServiceImpl")
public class AdStatServiceImpl extends BaseServiceImpl<AdStat, Long> implements AdStatService
{
    @Resource(name = "adStatDaoImpl")
    private AdStatDao adStatDao;

    @Resource(name = "adStatDaoImpl")
    public void setBaseDao(AdStatDao adStatDao){
        super.setBaseDao(adStatDao);
    }
}
