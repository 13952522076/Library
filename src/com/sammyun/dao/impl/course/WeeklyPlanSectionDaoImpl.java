package com.sammyun.dao.impl.course;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sammyun.Order;
import com.sammyun.dao.course.WeeklyPlanSectionDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.course.SchoolYearMng;
import com.sammyun.entity.course.WeeklyPlanSection;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.listener.InitListener;

@Repository("weeklyPlanSectionDaoImpl")
public class WeeklyPlanSectionDaoImpl extends BaseDaoImpl<WeeklyPlanSection, Long> implements WeeklyPlanSectionDao
{
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(InitListener.class);

    @Override
    public List<WeeklyPlanSection> findByClassAndSchoolYear(DictSchool dictSchool,SchoolYearMng schoolYearMng, DictClass dictClass,
            List<Order> orders)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<WeeklyPlanSection> criteriaQuery = criteriaBuilder.createQuery(WeeklyPlanSection.class);
        Root<WeeklyPlanSection> root = criteriaQuery.from(WeeklyPlanSection.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (dictSchool != null) {
            restrictions = criteriaBuilder.and(
                    restrictions,
                    criteriaBuilder.equal(
                            root.get("dictSchool"),dictSchool));
        }
        if (schoolYearMng != null) {
            restrictions = criteriaBuilder.and(
                    restrictions,
                    criteriaBuilder.equal(
                            root.get("schoolYearMng"),schoolYearMng));
        }
        if (dictClass != null) {
            restrictions = criteriaBuilder.and(
                    restrictions,
                    criteriaBuilder.equal(
                            root.get("dictClass"), dictClass));
        }
        criteriaQuery.where(restrictions);
        return super.findList(criteriaQuery, null, null, null, orders);
    }

    @Override
    public List<WeeklyPlanSection> findBySchool(DictSchool dictSchool)
    {
        if (dictSchool == null)
        {
            logger.info("WeeklyPlanSectionDaoImpl findBySchool: " + "dictSchool is null");
            return null;
        }
        try
        {
            String jpql = "select weeklyPlanSection from WeeklyPlanSection weeklyPlanSection where weeklyPlanSection.dictSchool = :dictSchool";
            return entityManager.createQuery(jpql, WeeklyPlanSection.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                    "dictSchool", dictSchool).getResultList();
        }
        catch (NoResultException e)
        {
            logger.error("WeeklyPlanSectionDaoImpl findBySchool: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<WeeklyPlanSection> findByClass(DictClass dictClass)
    {
        if (dictClass == null)
        {
            logger.info("WeeklyPlanSectionDaoImpl findByClass: " + "dictSchool is null");
            return null;
        }
        try
        {
            String jpql = "select weeklyPlanSection from WeeklyPlanSection weeklyPlanSection where weeklyPlanSection.dictClass = :dictClass";
            return entityManager.createQuery(jpql, WeeklyPlanSection.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                    "dictClass", dictClass).getResultList();
        }
        catch (NoResultException e)
        {
            logger.error("WeeklyPlanSectionDaoImpl findByClass: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<WeeklyPlanSection> findBySchoolYearMng(SchoolYearMng schoolYearMng)
    {
        if (schoolYearMng == null)
        {
            logger.info("WeeklyPlanSectionDaoImpl findBySchoolYearMng: " + "schoolYearMng is null");
            return null;
        }
        try
        {
            String jpql = "select weeklyPlanSection from WeeklyPlanSection weeklyPlanSection where weeklyPlanSection.schoolYearMng = :schoolYearMng";
            return entityManager.createQuery(jpql, WeeklyPlanSection.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                    "schoolYearMng", schoolYearMng).getResultList();
        }
        catch (NoResultException e)
        {
            logger.error("WeeklyPlanSectionDaoImpl findBySchoolYearMng: " + e.getMessage());
            return null;
        }
    }

}
