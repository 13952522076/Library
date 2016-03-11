package com.sammyun.service.impl.bus;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.bus.BusLineRuntimeService;

import com.sammyun.dao.bus.BusLineRuntimeDao;
import com.sammyun.entity.bus.BusLineRuntime;

/**
 * BusLineRuntime * ServiceImpl - 班车营运时间数据
 * 


 */

@Service("busLineRuntimeServiceImpl")
public class BusLineRuntimeServiceImpl extends BaseServiceImpl<BusLineRuntime, Long> implements BusLineRuntimeService {

    @Resource(name = "busLineRuntimeDaoImpl")
    private BusLineRuntimeDao busLineRuntimeDao;

    @Resource(name = "busLineRuntimeDaoImpl")
    public void setBaseDao(BusLineRuntimeDao busLineRuntimeDao){
        super.setBaseDao(busLineRuntimeDao);
    }


}
