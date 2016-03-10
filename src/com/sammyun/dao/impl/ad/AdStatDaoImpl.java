package com.sammyun.dao.impl.ad;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.ad.AdStatDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.ad.AdStat;

/**
 *  DaoImpl - 广告统计 
 * 
 * @author chenyunfeng
 * @version [版本号, Aug 28, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository("adStatDaoImpl")
public class AdStatDaoImpl extends BaseDaoImpl<AdStat, Long> implements AdStatDao
{

}
