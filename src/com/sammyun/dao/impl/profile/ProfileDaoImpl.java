package com.sammyun.dao.impl.profile;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.profile.ProfileDao;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.profile.Profile;

/**
 * Profiles * DaoImpl - 学校概况数据
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("profileDaoImpl")
public class ProfileDaoImpl extends BaseDaoImpl<Profile, Long> implements ProfileDao
{

    @Override
    public Profile findBySchool(DictSchool dictSchool)
    {
        if (dictSchool == null)
        {
            return null;
        }
        String jpql = "select profile from Profile profile where profile.dictSchool = :dictSchool";
        try
        {
            return entityManager.createQuery(jpql, Profile.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                    "dictSchool", dictSchool).getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

}
