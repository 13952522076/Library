package com.sammyun.service.impl.bus;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.bus.BusCarService;

import com.sammyun.dao.bus.BusCarDao;
import com.sammyun.entity.bus.BusCar;

/**
 * BusCar * ServiceImpl - 车辆数据
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("busCarServiceImpl")
public class BusCarServiceImpl extends BaseServiceImpl<BusCar, Long> implements BusCarService {

    @Resource(name = "busCarDaoImpl")
    private BusCarDao busCarDao;

    @Resource(name = "busCarDaoImpl")
    public void setBaseDao(BusCarDao busCarDao){
        super.setBaseDao(busCarDao);
    }

	
}
