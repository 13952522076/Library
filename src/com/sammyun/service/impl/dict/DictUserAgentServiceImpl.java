package com.sammyun.service.impl.dict;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.dict.DictUserAgentService;

import com.sammyun.dao.dict.DictUserAgentDao;
import com.sammyun.entity.dict.DictUserAgent;

/**
 * DictUserAgent * ServiceImpl - 用户浏览器数据字典
 * 


 */
@Service("dictUserAgentServiceImpl")
public class DictUserAgentServiceImpl extends BaseServiceImpl<DictUserAgent, Long> implements DictUserAgentService {

    @Resource(name = "dictUserAgentDaoImpl")
    private DictUserAgentDao dictUserAgentDao;

    @Resource(name = "dictUserAgentDaoImpl")
    public void setBaseDao(DictUserAgentDao dictUserAgentDao){
        super.setBaseDao(dictUserAgentDao);
    }


}
