package com.sammyun.service.impl.other;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.other.LoginlogService;

import com.sammyun.dao.other.LoginlogDao;
import com.sammyun.entity.other.Loginlog;

/**
 * Loginlog * ServiceImpl - 登录日志
 * 
 * @author Sencloud Team

 */
@Service("loginlogServiceImpl")
public class LoginlogServiceImpl extends BaseServiceImpl<Loginlog, Long> implements LoginlogService {

    @Resource(name = "loginlogDaoImpl")
    private LoginlogDao loginlogDao;

    @Resource(name = "loginlogDaoImpl")
    public void setBaseDao(LoginlogDao loginlogDao){
        super.setBaseDao(loginlogDao);
    }


}
