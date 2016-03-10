package com.sammyun.dao.impl.dict;

import javax.persistence.FlushModeType;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.dict.DictSchoolDao;
import com.sammyun.entity.dict.DictSchool;

/**
 * DictSchool * DaoImpl - 学校
 * 
 * @author Sencloud Team

 */
@Repository("dictSchoolDaoImpl")
public class DictSchoolDaoImpl extends BaseDaoImpl<DictSchool, Long> implements DictSchoolDao  {

    @Override
    public boolean codeExists(String code)
    {
        if (code == null)
        {
            return false;
        }
        String jpql = "select count(*) from DictSchool dictSchool where lower(dictSchool.code) = lower(:code)";
        Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "code", code).getSingleResult();
        return count > 0;
    }

}
