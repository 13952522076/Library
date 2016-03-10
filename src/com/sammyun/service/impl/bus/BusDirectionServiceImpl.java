package com.sammyun.service.impl.bus;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.bus.BusDirectionService;

import com.sammyun.dao.bus.BusDirectionDao;
import com.sammyun.entity.bus.BusDirection;

/**
 * BusDirection * ServiceImpl - 线路方向数据
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("busDirectionServiceImpl")
public class BusDirectionServiceImpl extends BaseServiceImpl<BusDirection, Long> implements BusDirectionService {

    @Resource(name = "busDirectionDaoImpl")
    private BusDirectionDao busDirectionDao;

    @Resource(name = "busDirectionDaoImpl")
    public void setBaseDao(BusDirectionDao busDirectionDao){
        super.setBaseDao(busDirectionDao);
    }


}
