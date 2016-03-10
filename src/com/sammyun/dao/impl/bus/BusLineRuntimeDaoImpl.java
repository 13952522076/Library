package com.sammyun.dao.impl.bus;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.bus.BusLineRuntimeDao;
import com.sammyun.entity.bus.BusLineRuntime;

/**
 * BusLineRuntime * DaoImpl - 班车营运时间数据
 * 
 * @author Sencloud Team

 */

@Repository("busLineRuntimeDaoImpl")
public class BusLineRuntimeDaoImpl extends BaseDaoImpl<BusLineRuntime, Long> implements BusLineRuntimeDao  {

}
