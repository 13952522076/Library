package com.sammyun.service.impl.bus;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.bus.BusLineDriverService;

import com.sammyun.dao.bus.BusLineDriverDao;
import com.sammyun.entity.bus.BusLineDriver;

/**
 * BusLineDriver * ServiceImpl - 线路车辆司机的关联关系
 * 


 */

@Service("busLineDriverServiceImpl")
public class BusLineDriverServiceImpl extends BaseServiceImpl<BusLineDriver, Long> implements BusLineDriverService {

    @Resource(name = "busLineDriverDaoImpl")
    private BusLineDriverDao busLineDriverDao;

    @Resource(name = "busLineDriverDaoImpl")
    public void setBaseDao(BusLineDriverDao busLineDriverDao){
        super.setBaseDao(busLineDriverDao);
    }


}
