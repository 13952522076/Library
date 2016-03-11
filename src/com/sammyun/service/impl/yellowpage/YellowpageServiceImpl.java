package com.sammyun.service.impl.yellowpage;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.yellowpage.YellowpageService;

import com.sammyun.dao.yellowpage.YellowpageDao;
import com.sammyun.entity.yellowpage.Yellowpage;

/**
 * Yellowpage * ServiceImpl - 黄页数据
 * 


 */
@Service("yellowpageServiceImpl")
public class YellowpageServiceImpl extends BaseServiceImpl<Yellowpage, Long> implements YellowpageService {

    @Resource(name = "yellowpageDaoImpl")
    private YellowpageDao yellowpageDao;

    @Resource(name = "yellowpageDaoImpl")
    public void setBaseDao(YellowpageDao yellowpageDao){
        super.setBaseDao(yellowpageDao);
    }


}
