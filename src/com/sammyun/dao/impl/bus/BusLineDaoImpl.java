package com.sammyun.dao.impl.bus;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.bus.BusLineDao;
import com.sammyun.entity.bus.BusLine;

/**
 * BusLine * DaoImpl - 班车线路数据
 * 


 */
@Repository("busLineDaoImpl")
public class BusLineDaoImpl extends BaseDaoImpl<BusLine, Long> implements BusLineDao  {

}
