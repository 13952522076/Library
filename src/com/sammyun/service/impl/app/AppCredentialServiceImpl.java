package com.sammyun.service.impl.app;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.app.AppCredentialDao;
import com.sammyun.entity.app.AppCredential;
import com.sammyun.service.app.AppCredentialService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * ServiceImpl - 应用API授权
 * 
 * @author Sencloud Team

 */
@Service("appCredentialServiceImpl")
public class AppCredentialServiceImpl extends BaseServiceImpl<AppCredential, Long> implements AppCredentialService
{
    @Resource(name = "appCredentialDaoImpl")
    private AppCredentialDao appCredentialDao;
    
    @Resource(name = "appCredentialDaoImpl")
    public void setBaseDao(AppCredentialDao appCredentialDao){
        super.setBaseDao(appCredentialDao);
    }
    
}
