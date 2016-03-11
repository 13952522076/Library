package com.sammyun.dao.impl.bus;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.bus.BusCarDao;
import com.sammyun.entity.bus.BusCar;

/**
 * BusCar * DaoImpl - 车辆数据
 * 


 */
@Repository("busCarDaoImpl")
public class BusCarDaoImpl extends BaseDaoImpl<BusCar, Long> implements BusCarDao  {
	
}
