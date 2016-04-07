package com.sammyun.dao.impl.stu;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.stu.StudentWorksDao;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.stu.StudentWorks;

/**
 * StudentWorks * DaoImpl - 学生作品
 */
@Repository("studentWorksDaoImpl")
public class StudentWorksDaoImpl extends BaseDaoImpl<StudentWorks, Long> implements StudentWorksDao
{

    @Override
    public List<StudentWorks> getListByDictStudent(DictStudent dictStudent)
    {
        try
        {
            String jpql = "select studentWorks from StudentWorks studentWorks where studentWorks.dictStudent = :dictStudent";
            return entityManager.createQuery(jpql, StudentWorks.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                    "dictStudent", dictStudent).getResultList();
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
