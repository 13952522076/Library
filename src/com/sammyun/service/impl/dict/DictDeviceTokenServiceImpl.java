package com.sammyun.service.impl.dict;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.dict.DictDeviceTokenService;

import com.sammyun.dao.dict.DictDeviceTokenDao;
import com.sammyun.entity.dict.DictDeviceToken;

/**
 * DictDeviceToken * ServiceImpl - 设备凭证数据字典
 * 


 */
@Service("dictDeviceTokenServiceImpl")
public class DictDeviceTokenServiceImpl extends BaseServiceImpl<DictDeviceToken, Long> implements DictDeviceTokenService {

    @Resource(name = "dictDeviceTokenDaoImpl")
    private DictDeviceTokenDao dictDeviceTokenDao;

    @Resource(name = "dictDeviceTokenDaoImpl")
    public void setBaseDao(DictDeviceTokenDao dictDeviceTokenDao){
        super.setBaseDao(dictDeviceTokenDao);
    }


}
