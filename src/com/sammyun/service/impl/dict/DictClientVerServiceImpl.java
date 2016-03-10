package com.sammyun.service.impl.dict;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.dict.DictClientVerService;

import com.sammyun.dao.dict.DictClientVerDao;
import com.sammyun.entity.dict.DictClientVer;

/**
 * DictClientVer * ServiceImpl - 客户端版本号数据字典
 * 
 * @author Sencloud Team

 */
@Service("dictClientVerServiceImpl")
public class DictClientVerServiceImpl extends BaseServiceImpl<DictClientVer, Long> implements DictClientVerService {

    @Resource(name = "dictClientVerDaoImpl")
    private DictClientVerDao dictClientVerDao;

    @Resource(name = "dictClientVerDaoImpl")
    public void setBaseDao(DictClientVerDao dictClientVerDao){
        super.setBaseDao(dictClientVerDao);
    }


}
