package com.sammyun.service.impl.dict;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.dict.DictDeviceModelService;

import com.sammyun.dao.dict.DictDeviceModelDao;
import com.sammyun.entity.dict.DictDeviceModel;

/**
 * DictDeviceModel * ServiceImpl - 设备数据字典
 * 


 */
@Service("dictDeviceModelServiceImpl")
public class DictDeviceModelServiceImpl extends BaseServiceImpl<DictDeviceModel, Long> implements DictDeviceModelService {

    @Resource(name = "dictDeviceModelDaoImpl")
    private DictDeviceModelDao dictDeviceModelDao;

    @Resource(name = "dictDeviceModelDaoImpl")
    public void setBaseDao(DictDeviceModelDao dictDeviceModelDao){
        super.setBaseDao(dictDeviceModelDao);
    }


}
