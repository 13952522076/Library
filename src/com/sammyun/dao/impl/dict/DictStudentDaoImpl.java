package com.sammyun.dao.impl.dict;

import java.util.List;
import java.util.Set;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.dict.DictStudentDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictStudent;

/**
 * DictStudent * DaoImpl - 学生
 */
@Repository("dictStudentDaoImpl")
public class DictStudentDaoImpl extends BaseDaoImpl<DictStudent, Long> implements DictStudentDao
{

    @Override
    public List<DictStudent> getStudentByClass(DictClass dictclass)
    {
        // TODO Auto-generated method stub
        try
        {
            String jpql = "select dictStudent from DictStudent dictStudent where dictStudent.dictClass = :dictclass";
            return entityManager.createQuery(jpql, DictStudent.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                    "dictclass", dictclass).getResultList();
        }
        catch (NoResultException e)
        {
            return null;
        }

    }

    @Override
    public List<DictStudent> findByStudentNo(String studentNo, DictSchool dictSchool)
    {
        if (studentNo == null)
        {
            return null;
        }
        if (dictSchool == null)
        {
            return null;
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DictStudent> criteriaQuery = criteriaBuilder.createQuery(DictStudent.class);
        Root<DictStudent> root = criteriaQuery.from(DictStudent.class);
        Join<DictStudent, DictClass> dictClass = root.join("dictClass");
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        restrictions = criteriaBuilder.and(restrictions,
                criteriaBuilder.equal(root.<String> get("studentNo"), studentNo));
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(dictClass.get("dictSchool"), dictSchool));
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
        // try
        // {
        // String jpql =
        // "select dictStudent from DictStudent dictStudent where lower(dictStudent.studentNo) = lower(:studentNo)";
        // return entityManager.createQuery(jpql,
        // DictStudent.class).setFlushMode(FlushModeType.COMMIT).setParameter(
        // "studentNo", studentNo).getSingleResult();
        // }
        // catch (NoResultException e)
        // {
        // return null;
        // }
    }

    /**
     * 根据学校查询学生 <功能详细描述>
     * 
     * @param dictSchool
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<DictStudent> findStudentsBySchool(DictSchool dictSchool)
    {
        if (dictSchool == null)
        {
            return null;
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DictStudent> criteriaQuery = criteriaBuilder.createQuery(DictStudent.class);
        Root<DictStudent> root = criteriaQuery.from(DictStudent.class);
        Join<DictStudent, DictClass> dictClass = root.join("dictClass");
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(dictClass.get("dictSchool"), dictSchool));
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

    @Override
    public boolean studentNoExists(String studentNo, Set<DictClass> dictClasses)
    {
        if ((studentNo == null) || (dictClasses == null))
        {
            return false;
        }
        if (dictClasses.size() == 0)
        {
            return false;
        }
        String jpql = "select count(*) from DictStudent dictStudent where lower(dictStudent.studentNo) = lower(:studentNo) and dictStudent.dictClass in :dictClasses";
        Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "studentNo", studentNo).setParameter("dictClasses", dictClasses).getSingleResult();
        return count > 0;
    }

    @Override
    public boolean studentNoUnique(String studentNo, DictSchool dictSchool)
    {
        if (studentNo == null)
        {
            return false;
        }
        if (dictSchool == null)
        {
            return false;
        }
        Set<DictClass> dictClasses = dictSchool.getDictClasses();
        if (dictClasses == null || dictClasses.size() == 0)
        {
            return false;
        }
        String jpql = "select count(*) from DictStudent dictStudent where lower(dictStudent.studentNo) = lower(:studentNo) and dictStudent.dictClass in :dictClasses";
        Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "studentNo", studentNo).setParameter("dictClasses", dictClasses).getSingleResult();
        return count > 0;
    }

    @Override
    public DictStudent findByStudentNoSingle(String studentNo, DictSchool dictSchool)
    {
        if (studentNo == null)
        {
            return null;
        }
        if (dictSchool == null)
        {
            return null;
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DictStudent> criteriaQuery = criteriaBuilder.createQuery(DictStudent.class);
        Root<DictStudent> root = criteriaQuery.from(DictStudent.class);
        Join<DictStudent, DictClass> dictClass = root.join("dictClass");
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        restrictions = criteriaBuilder.and(restrictions,
                criteriaBuilder.equal(root.<String> get("studentNo"), studentNo));
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(dictClass.get("dictSchool"), dictSchool));
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getSingleResult();
    }

}
