package com.sammyun.service.impl.gd;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.gd.AtUserDao;
import com.sammyun.entity.gd.AtUser;
import com.sammyun.service.gd.AtUserService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * AtUser * ServiceImpl -
 * 


 */
@Service("atUserServiceImpl")
public class AtUserServiceImpl extends BaseServiceImpl<AtUser, Long> implements AtUserService
{

    @Resource(name = "atUserDaoImpl")
    private AtUserDao atUserDao;

    @Resource(name = "atUserDaoImpl")
    public void setBaseDao(AtUserDao atUserDao)
    {
        super.setBaseDao(atUserDao);
    }

}
