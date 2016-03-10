package com.sammyun.service.impl.other;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.other.ClientversionService;

import com.sammyun.dao.other.ClientversionDao;
import com.sammyun.entity.other.Clientversion;

/**
 * Clientversion * ServiceImpl - 客户端版本管理
 * 
 * @author Sencloud Team

 */
@Service("clientversionServiceImpl")
public class ClientversionServiceImpl extends BaseServiceImpl<Clientversion, Long> implements ClientversionService {

    @Resource(name = "clientversionDaoImpl")
    private ClientversionDao clientversionDao;

    @Resource(name = "clientversionDaoImpl")
    public void setBaseDao(ClientversionDao clientversionDao){
        super.setBaseDao(clientversionDao);
    }


}
