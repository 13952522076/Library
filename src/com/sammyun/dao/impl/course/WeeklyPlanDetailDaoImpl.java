package com.sammyun.dao.impl.course;

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.course.WeeklyPlanDetailDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.course.WeeklyPlanDetail;
import com.sammyun.entity.course.WeeklyPlanSection;

/**
 * Dao - 周计划详情
 * 
 * @author xutianlong
 * @version [版本号, May 2, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository("weeklyPlanDetailDaoImpl")
public class WeeklyPlanDetailDaoImpl extends BaseDaoImpl<WeeklyPlanDetail, Long> implements WeeklyPlanDetailDao
{

    /**
     * 删除 周计划详情
     * 
     * @param weeklyPlanSection
     * @see [类、类#方法、类#成员]
     */
    @Override
    public void deleteByWeeklyPlanSection(WeeklyPlanSection weeklyPlanSection)
    {

        String jpql = "delete from WeeklyPlanDetail weeklyPlanDetail where weeklyPlanDetail.weeklyPlanSection = :weeklyPlanSection";
        entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("weeklyPlanSection",
                weeklyPlanSection).executeUpdate();

    }

}
