package com.sammyun.service.impl.course;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.course.WeeklyPlanDetailDao;
import com.sammyun.entity.course.WeeklyPlanDetail;
import com.sammyun.entity.course.WeeklyPlanSection;
import com.sammyun.service.course.WeeklyPlanDetailService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * Service - 周计划详情
 * 
 * @version [版本号, Apr 13, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

@Service("weeklyPlanDetailServiceImpl")
public class WeeklyPlanDetailServiceImpl extends BaseServiceImpl<WeeklyPlanDetail, Long> implements
        WeeklyPlanDetailService
{

    @Resource(name = "weeklyPlanDetailDaoImpl")
    private WeeklyPlanDetailDao weeklyPlanDetailDao;

    @Resource(name = "weeklyPlanDetailDaoImpl")
    public void setBaseDao(WeeklyPlanDetailDao weeklyPlanDetailDao)
    {
        super.setBaseDao(weeklyPlanDetailDao);
    }

    /**
     * 删除 周计划详情
     * 
     * @param weeklyPlanSection
     * @see [类、类#方法、类#成员]
     */
    @Override
    public void deleteByWeeklyPlanSection(WeeklyPlanSection weeklyPlanSection)
    {
        weeklyPlanDetailDao.deleteByWeeklyPlanSection(weeklyPlanSection);
    }

}
