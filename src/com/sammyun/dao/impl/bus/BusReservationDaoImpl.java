package com.sammyun.dao.impl.bus;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.bus.BusReservationDao;
import com.sammyun.entity.bus.BusReservation;

/**
 * BusReservation * DaoImpl - 预约数据
 * 


 */

@Repository("busReservationDaoImpl")
public class BusReservationDaoImpl extends BaseDaoImpl<BusReservation, Long> implements BusReservationDao  {

}
