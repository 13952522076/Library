package com.sammyun.service.impl.dict;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.dict.DictSchoolService;

import com.sammyun.dao.dict.DictSchoolDao;
import com.sammyun.entity.dict.DictSchool;

/**
 * DictSchool * ServiceImpl - 学校
 * 


 */
@Service("dictSchoolServiceImpl")
public class DictSchoolServiceImpl extends BaseServiceImpl<DictSchool, Long> implements DictSchoolService {

    @Resource(name = "dictSchoolDaoImpl")
    private DictSchoolDao dictSchoolDao;

    @Resource(name = "dictSchoolDaoImpl")
    public void setBaseDao(DictSchoolDao dictSchoolDao){
        super.setBaseDao(dictSchoolDao);
    }

    @Override
    public boolean codeExists(String code)
    {
        return dictSchoolDao.codeExists(code);
    }


}
