package com.sammyun.service.impl.dict;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.dict.DictCampusService;

import com.sammyun.dao.dict.DictCampusDao;
import com.sammyun.entity.dict.DictCampus;

/**
 * DictCampus * ServiceImpl - 校区
 * 


 */
@Service("dictCampusServiceImpl")
public class DictCampusServiceImpl extends BaseServiceImpl<DictCampus, Long> implements DictCampusService {

    @Resource(name = "dictCampusDaoImpl")
    private DictCampusDao dictCampusDao;

    @Resource(name = "dictCampusDaoImpl")
    public void setBaseDao(DictCampusDao dictCampusDao){
        super.setBaseDao(dictCampusDao);
    }


}
