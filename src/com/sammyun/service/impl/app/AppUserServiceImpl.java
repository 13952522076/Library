package com.sammyun.service.impl.app;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.app.AppUserDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.app.App;
import com.sammyun.entity.app.AppUser;
import com.sammyun.entity.app.App.OperatingSystem;
import com.sammyun.service.app.AppUserService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * ServiceImpl - 用户应用清单
 * 
 * @author Sencloud Team

 */
@Service("appUserServiceImpl")
public class AppUserServiceImpl extends BaseServiceImpl<AppUser, Long> implements AppUserService 
{
    @Resource(name = "appUserDaoImpl")
    private AppUserDao appUserDao;

    @Resource(name = "appUserDaoImpl")
    public void setBaseDao(AppUserDao appUserDao) {
        super.setBaseDao(appUserDao);
    }

    @Override
    public Page<AppUser> findByMember(Member member, Boolean isDelete, OperatingSystem operatingSystem, Pageable pageable)
    {
        return appUserDao.findByMember(member, isDelete, operatingSystem, pageable);
    }
    
    @Override
    public AppUser findByParam(Member member, App app){
    	return appUserDao.findByParam(member, app);
    }

}
