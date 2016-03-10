package com.sammyun.service.impl.ad;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.ad.AdAreaDao;
import com.sammyun.entity.ad.AdArea;
import com.sammyun.service.ad.AdAreaService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 *  ServiceImpl - 投放 区域（定向地区）
 * 
 * @author chenyunfeng
 * @version [版本号, Aug 28, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("adAreaServiceImpl")
public class AdAreaServiceImpl extends BaseServiceImpl<AdArea, Long> implements AdAreaService 
{
    @Resource(name = "adAreaDaoImpl")
    private AdAreaDao adAreaDao;

    @Resource(name = "adAreaDaoImpl")
    public void setBaseDao(AdAreaDao adAreaDao){
        super.setBaseDao(adAreaDao);
    }

    @Override
    public List<AdArea> finListByFullName(String fullName)
    {
        return adAreaDao.finListByFullName(fullName);
    }

}
