package com.sammyun.dao.impl.bus;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.bus.BusDriverDao;
import com.sammyun.entity.bus.BusDriver;

/**
 * BusDriver * DaoImpl - 驾驶员数据
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("busDriverDaoImpl")
public class BusDriverDaoImpl extends BaseDaoImpl<BusDriver, Long> implements BusDriverDao  {

}
