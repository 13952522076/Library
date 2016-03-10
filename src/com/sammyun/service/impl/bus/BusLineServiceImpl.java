package com.sammyun.service.impl.bus;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.bus.BusLineService;

import com.sammyun.dao.bus.BusLineDao;
import com.sammyun.entity.bus.BusLine;

/**
 * BusLine * ServiceImpl - 班车线路数据
 * 
 * @author Sencloud Team

 */
@Service("busLineServiceImpl")
public class BusLineServiceImpl extends BaseServiceImpl<BusLine, Long> implements BusLineService {

    @Resource(name = "busLineDaoImpl")
    private BusLineDao busLineDao;

    @Resource(name = "busLineDaoImpl")
    public void setBaseDao(BusLineDao busLineDao){
        super.setBaseDao(busLineDao);
    }


}
