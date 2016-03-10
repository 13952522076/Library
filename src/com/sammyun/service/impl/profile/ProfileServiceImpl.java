package com.sammyun.service.impl.profile;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.profile.ProfileDao;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.profile.Profile;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.profile.ProfileService;

/**
 * ServiceImpl - 学校概况数据
 * 
 * @author Sencloud Team

 */
@Service("profileServiceImpl")
public class ProfileServiceImpl extends BaseServiceImpl<Profile, Long> implements ProfileService
{

    @Resource(name = "profileDaoImpl")
    private ProfileDao profileDao;

    @Resource(name = "profileDaoImpl")
    public void setBaseDao(ProfileDao profileDao)
    {
        super.setBaseDao(profileDao);
    }

    @Override
    public Profile findBySchool(DictSchool dictSchool)
    {
        return profileDao.findBySchool(dictSchool);
    }

}
