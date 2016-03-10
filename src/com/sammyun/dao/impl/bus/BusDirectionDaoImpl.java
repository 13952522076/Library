package com.sammyun.dao.impl.bus;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.bus.BusDirectionDao;
import com.sammyun.entity.bus.BusDirection;

/**
 * BusDirection * DaoImpl - 线路方向数据
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("busDirectionDaoImpl")
public class BusDirectionDaoImpl extends BaseDaoImpl<BusDirection, Long> implements BusDirectionDao  {

}
