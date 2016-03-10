package com.sammyun.dao.impl.ad;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.ad.AdTimeSlotDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.ad.AdTimeSlot;

/**
 *  DaoImpl - 定向投放时间段 
 * 
 * @author chenyunfeng
 * @version [版本号, Aug 28, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository("adTimeSlotDaoImpl")
public class AdTimeSlotDaoImpl extends BaseDaoImpl<AdTimeSlot, Long> implements AdTimeSlotDao
{

}
