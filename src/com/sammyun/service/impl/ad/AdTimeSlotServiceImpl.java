package com.sammyun.service.impl.ad;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.ad.AdTimeSlotDao;
import com.sammyun.entity.ad.AdTimeSlot;
import com.sammyun.service.ad.AdTimeSlotService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 *  ServiceImpl - 定向投放时间段 
 * 
 * @author chenyunfeng
 * @version [版本号, Aug 28, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("adTimeSlotServiceImpl")
public class AdTimeSlotServiceImpl extends BaseServiceImpl<AdTimeSlot, Long> implements AdTimeSlotService
{
    @Resource(name = "adTimeSlotDaoImpl")
    private AdTimeSlotDao adTimeSlotDao;

    @Resource(name = "adTimeSlotDaoImpl")
    public void setBaseDao(AdTimeSlotDao adTimeSlotDao){
        super.setBaseDao(adTimeSlotDao);
    }
}
