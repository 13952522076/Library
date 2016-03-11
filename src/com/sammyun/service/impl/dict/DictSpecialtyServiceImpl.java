package com.sammyun.service.impl.dict;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.dict.DictSpecialtyService;

import com.sammyun.dao.dict.DictSpecialtyDao;
import com.sammyun.entity.dict.DictSpecialty;

/**
 * DictSpecialty * ServiceImpl - 专业
 * 


 */
@Service("dictSpecialtyServiceImpl")
public class DictSpecialtyServiceImpl extends BaseServiceImpl<DictSpecialty, Long> implements DictSpecialtyService {

    @Resource(name = "dictSpecialtyDaoImpl")
    private DictSpecialtyDao dictSpecialtyDao;

    @Resource(name = "dictSpecialtyDaoImpl")
    public void setBaseDao(DictSpecialtyDao dictSpecialtyDao){
        super.setBaseDao(dictSpecialtyDao);
    }


}
