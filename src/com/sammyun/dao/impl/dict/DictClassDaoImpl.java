package com.sammyun.dao.impl.dict;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import com.sammyun.dao.impl.BaseDaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sammyun.dao.dict.DictClassDao;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.listener.InitListener;

/**
 * DictClass * DaoImpl - 学校
 * 
 * @author Sencloud Team

 */
@Repository("dictClassDaoImpl")
public class DictClassDaoImpl extends BaseDaoImpl<DictClass, Long> implements DictClassDao
{
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(InitListener.class);

    @Override
    public List<DictClass> getClassesBySchool(DictSchool school)
    {
        if (school == null)
        {
            logger.info("DictClassDaoImpl getClassesBySchool: " + "school is null");
            return null;
        }
        try
        {
            String jpql = "select dictClass from DictClass dictClass where dictClass.dictSchool = :school";
            return entityManager.createQuery(jpql, DictClass.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                    "school", school).getResultList();
        }
        catch (NoResultException e)
        {
            logger.error("DictClassDaoImpl getClassesBySchool: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean codeExists(String code, DictSchool dictSchool)
    {
        if (code == null)
        {
            return false;
        }
        String jpql = "select count(*) from DictClass dictClass where lower(dictClass.code) = lower(:code) and dictClass.dictSchool = :dictSchool";
        Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "code", code).setParameter("dictSchool", dictSchool).getSingleResult();
        return count > 0;
    }

	@Override
	public List<DictClass> getClassByName(String name, DictSchool dictSchool) {
		if (dictSchool == null)
        {
            logger.info("DictClassDaoImpl getClassesBySchool: " + "dictSchool is null");
            return null;
        }
        try
        {
            String jpql = "select dictClass from DictClass dictClass where dictClass.dictSchool = :dictSchool and dictClass.name = :name";
            return entityManager.createQuery(jpql, DictClass.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                    "dictSchool", dictSchool).setParameter("name", name).getResultList();
        }
        catch (NoResultException e)
        {
            logger.error("DictClassDaoImpl getClassesBySchool: " + e.getMessage());
            return null;
        }
	}
	
	@Override
	public boolean classNameExist(String className, DictSchool dictSchool)
	{
	    if (className == null)
	    {
	    	return false;
	    }
	    String jpql = "select count(*) from DictClass dictClass where lower(dictClass.name) = lower(:className) and dictClass.dictSchool = :dictSchool";
	    Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter(
	                "className", className).setParameter("dictSchool", dictSchool).getSingleResult();
	    return count > 0;
	} 
}
