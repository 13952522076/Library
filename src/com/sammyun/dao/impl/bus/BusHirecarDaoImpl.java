package com.sammyun.dao.impl.bus;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.bus.BusHirecarDao;
import com.sammyun.entity.bus.BusHirecar;

/**
 * BusHirecar * DaoImpl - 租车预约数据
 * 
 * @author Sencloud Team

 */
@Repository("busHirecarDaoImpl")
public class BusHirecarDaoImpl extends BaseDaoImpl<BusHirecar, Long> implements BusHirecarDao  {

}
