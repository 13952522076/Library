package com.sammyun.service.impl.bus;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.bus.BusHirecarService;

import com.sammyun.dao.bus.BusHirecarDao;
import com.sammyun.entity.bus.BusHirecar;

/**
 * BusHirecar * ServiceImpl - 租车预约数据
 * 
 * @author Sencloud Team

 */
@Service("busHirecarServiceImpl")
public class BusHirecarServiceImpl extends BaseServiceImpl<BusHirecar, Long> implements BusHirecarService {

    @Resource(name = "busHirecarDaoImpl")
    private BusHirecarDao busHirecarDao;

    @Resource(name = "busHirecarDaoImpl")
    public void setBaseDao(BusHirecarDao busHirecarDao){
        super.setBaseDao(busHirecarDao);
    }


}
