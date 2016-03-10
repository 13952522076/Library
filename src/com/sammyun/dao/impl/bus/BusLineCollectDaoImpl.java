package com.sammyun.dao.impl.bus;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.bus.BusLineCollectDao;
import com.sammyun.entity.bus.BusLineCollect;

/**
 * BusLineCollect * DaoImpl - 线路收藏数据
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("busLineCollectDaoImpl")
public class BusLineCollectDaoImpl extends BaseDaoImpl<BusLineCollect, Long> implements BusLineCollectDao  {

}
