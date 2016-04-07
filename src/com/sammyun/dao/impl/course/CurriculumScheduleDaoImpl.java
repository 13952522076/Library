package com.sammyun.dao.impl.course;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.Order;
import com.sammyun.dao.course.CurriculumScheduleDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.course.CurriculumSchedule;
import com.sammyun.entity.course.SchoolYearMng;
import com.sammyun.entity.dict.DictClass;

@Repository("curriculumScheduleDaoImpl")
public class CurriculumScheduleDaoImpl extends BaseDaoImpl<CurriculumSchedule, Long> implements CurriculumScheduleDao
{

    /**
     * 根据班级和校历查询列表 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    // @Override
    // public List<CurriculumSchedule> findByClassAndSchoolYear(SchoolYearMng
    // schoolYearMng, DictClass dictClass)
    // {
    // String jpql =
    // "select curriculumSchedule from CurriculumSchedule curriculumSchedule where curriculumSchedule.schoolYearMng = :schoolYearMng and curriculumSchedule.dictClass = :dictClass";
    // List<CurriculumSchedule> curriculumSchedules = null;
    // try
    // {
    // TypedQuery<CurriculumSchedule> flushModel =
    // entityManager.createQuery(jpql, CurriculumSchedule.class).setFlushMode(
    // FlushModeType.COMMIT);
    // flushModel.setParameter("schoolYearMng", schoolYearMng);
    // flushModel.setParameter("dictClass", dictClass);
    // curriculumSchedules = (List<CurriculumSchedule>)
    // flushModel.getResultList();
    // }
    // catch (Exception e)
    // {
    // logger.error("查询课程表列表页面错误：" + e.getMessage());
    // }
    //
    // return curriculumSchedules;
    // }

    @Override
    public List<CurriculumSchedule> findByClassAndSchoolYear(SchoolYearMng schoolYearMng, DictClass dictClass,
            List<Order> orders)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CurriculumSchedule> criteriaQuery = criteriaBuilder.createQuery(CurriculumSchedule.class);
        Root<CurriculumSchedule> root = criteriaQuery.from(CurriculumSchedule.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();

        if (schoolYearMng != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("schoolYearMng"), schoolYearMng));
        }
        if (dictClass != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("dictClass"), dictClass));
        }
        criteriaQuery.where(restrictions);
        return super.findList(criteriaQuery, null, null, null, orders);
    }

    /**
     * 根据校历，班级，星期，课节查询是否存在课程 <功能详细描述>
     * 
     * @param schoolYearMng
     * @param dictClass
     * @param week
     * @param lessons
     * @return true存在 false不存在
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean courseExsit(SchoolYearMng schoolYearMng, DictClass dictClass, int week, String lessons)
    {
        // TODO Auto-generated method stub
        String jpql = "select count(*) from CurriculumSchedule curriculumSchedule where curriculumSchedule.schoolYearMng = :schoolYearMng and curriculumSchedule.dictClass = :dictClass and curriculumSchedule.week = :week and curriculumSchedule.lessons = :lessons";
        Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "schoolYearMng", schoolYearMng).setParameter("dictClass", dictClass).setParameter("week", week).setParameter(
                "lessons", lessons).getSingleResult();
        return count > 0;
    }

}
