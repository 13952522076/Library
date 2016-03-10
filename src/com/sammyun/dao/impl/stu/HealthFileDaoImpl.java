package com.sammyun.dao.impl.stu;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.impl.recipe.RecipeDaoImpl;
import com.sammyun.dao.stu.HealthFileDao;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.stu.HealthFile;

/**
 * HealthFile * DaoImpl - 健康档案
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("healthFileDaoImpl")
public class HealthFileDaoImpl extends BaseDaoImpl<HealthFile, Long> implements HealthFileDao
{
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(RecipeDaoImpl.class);

    @Override
    public HealthFile findByDictStudent(DictStudent dictStudent)
    {
        if (dictStudent == null)
        {
            return null;
        }
        try
        {
            String jpql = "select healthFile from HealthFile healthFile where healthFile.dictStudent = :dictStudent";
            return entityManager.createQuery(jpql, HealthFile.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                    "dictStudent", dictStudent).getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

}
