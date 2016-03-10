package com.sammyun.service.impl.bus;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.bus.BusDriverService;

import com.sammyun.dao.bus.BusDriverDao;
import com.sammyun.entity.bus.BusDriver;

/**
 * BusDriver * ServiceImpl - 驾驶员数据
 * 
 * @author Sencloud Team

 */
@Service("busDriverServiceImpl")
public class BusDriverServiceImpl extends BaseServiceImpl<BusDriver, Long> implements BusDriverService {

    @Resource(name = "busDriverDaoImpl")
    private BusDriverDao busDriverDao;

    @Resource(name = "busDriverDaoImpl")
    public void setBaseDao(BusDriverDao busDriverDao){
        super.setBaseDao(busDriverDao);
    }


}
