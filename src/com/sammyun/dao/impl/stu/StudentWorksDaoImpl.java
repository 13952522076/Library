package com.sammyun.dao.impl.stu;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.stu.StudentWorksDao;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.stu.StudentWorks;

/**
 * StudentWorks * DaoImpl - 学生作品
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("studentWorksDaoImpl")
public class StudentWorksDaoImpl extends BaseDaoImpl<StudentWorks, Long> implements StudentWorksDao
{
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(StudentWorksDaoImpl.class);

    @Override
    public List<StudentWorks> getListByDictStudent(DictStudent dictStudent)
    {
        try
        {
            String jpql = "select studentWorks from StudentWorks studentWorks where studentWorks.dictStudent = :dictStudent";
            return entityManager.createQuery(jpql, StudentWorks.class)
                    .setFlushMode(FlushModeType.COMMIT)
                    .setParameter("dictStudent", dictStudent)
                    .getResultList();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

    @Override
    public void deleteByDictStudent(DictStudent dictStudent)
    {
        String jpql = "delete from StudentWorks studentWorks where studentWorks.dictStudent = :dictStudent";
        entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("dictStudent", dictStudent).executeUpdate();
        
    }

    

}
