package com.sammyun.dao.course;

import com.sammyun.dao.BaseDao;
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
public interface WeeklyPlanDetailDao extends BaseDao<WeeklyPlanDetail, Long>
{
    /**
     * 删除 周计划详情
     * 
     * @param weeklyPlanSection
     * @see [类、类#方法、类#成员]
     */
    void deleteByWeeklyPlanSection(WeeklyPlanSection weeklyPlanSection);
}
