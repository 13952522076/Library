package com.sammyun.dao.impl.bus;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.bus.BusLineDriverDao;
import com.sammyun.entity.bus.BusLineDriver;

/**
 * BusLineDriver * DaoImpl - 线路车辆司机的关联关系
 * 
 * @author Sencloud Team

 */

@Repository("busLineDriverDaoImpl")
public class BusLineDriverDaoImpl extends BaseDaoImpl<BusLineDriver, Long> implements BusLineDriverDao  {

}
