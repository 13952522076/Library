package com.sammyun.dao.impl.course;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sammyun.Order;
import com.sammyun.controller.console.AttendanceController;
import com.sammyun.dao.course.SchoolYearMngDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.course.SchoolYearMng;
import com.sammyun.entity.dict.DictSchool;

/**
 * Dao - 学年管理
 * 
 * @author xutianlong
 * @version [版本号, Apr 13, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository("schoolYearMngDaoImpl")
public class SchoolYearMngDaoImpl extends BaseDaoImpl<SchoolYearMng, Long> implements SchoolYearMngDao
{

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);

    /**
     * 根据学校查找出学年列表 <功能详细描述>
     * 
     * @param dictSchool
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<SchoolYearMng> findBySchool(DictSchool dictSchool)
    {
        String jpql = "select schoolYearMng from SchoolYearMng schoolYearMng where 1=1 and schoolYearMng.dictSchool = :dictSchool";
        List<SchoolYearMng> schoolYearMngs = null;
        try
        {
            TypedQuery<SchoolYearMng> flushModel = entityManager.createQuery(jpql, SchoolYearMng.class).setFlushMode(
                    FlushModeType.COMMIT);
            flushModel.setParameter("dictSchool", dictSchool);
            schoolYearMngs = (List<SchoolYearMng>) flushModel.getResultList();
        }
        catch (Exception e)
        {
            logger.error("查询学年列表页面错误：" + e.getMessage());
        }

        return schoolYearMngs;
    }
    
    
    @Override
    public List<SchoolYearMng> findSchoolYearMng(DictSchool dictSchool,Boolean isShow,List<Order> orders)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SchoolYearMng> criteriaQuery = criteriaBuilder.createQuery(SchoolYearMng.class);
        Root<SchoolYearMng> root = criteriaQuery.from(SchoolYearMng.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (dictSchool != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("dictSchool"), dictSchool));
        }

        if (isShow != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isShow"), isShow));
        }
        criteriaQuery.where(restrictions);
        return super.findList(criteriaQuery, null, null, null, orders);
    }
}
