package com.sammyun.service.impl.bus;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.bus.BusLineCollectService;

import com.sammyun.dao.bus.BusLineCollectDao;
import com.sammyun.entity.bus.BusLineCollect;

/**
 * BusLineCollect * ServiceImpl - 线路收藏数据
 * 
 * @author Sencloud Team

 */
@Service("busLineCollectServiceImpl")
public class BusLineCollectServiceImpl extends BaseServiceImpl<BusLineCollect, Long> implements BusLineCollectService {

    @Resource(name = "busLineCollectDaoImpl")
    private BusLineCollectDao busLineCollectDao;

    @Resource(name = "busLineCollectDaoImpl")
    public void setBaseDao(BusLineCollectDao busLineCollectDao){
        super.setBaseDao(busLineCollectDao);
    }


}
