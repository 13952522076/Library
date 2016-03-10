package com.sammyun.service.impl.bus;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.bus.BusReservationService;

import com.sammyun.dao.bus.BusReservationDao;
import com.sammyun.entity.bus.BusReservation;

/**
 * BusReservation * ServiceImpl - 预约数据
 * 
 * @author Sencloud Team

 */

@Service("busReservationServiceImpl")
public class BusReservationServiceImpl extends BaseServiceImpl<BusReservation, Long> implements BusReservationService {

    @Resource(name = "busReservationDaoImpl")
    private BusReservationDao busReservationDao;

    @Resource(name = "busReservationDaoImpl")
    public void setBaseDao(BusReservationDao busReservationDao){
        super.setBaseDao(busReservationDao);
    }


}
