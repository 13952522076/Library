package com.sammyun.service.impl.course;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.Order;
import com.sammyun.dao.course.WeeklyPlanSectionDao;
import com.sammyun.entity.course.SchoolYearMng;
import com.sammyun.entity.course.WeeklyPlanSection;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.course.WeeklyPlanSectionService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * Service - 周计划段
 * 
 * @version [版本号, Apr 13, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

@Service("weeklyPlanSectionServiceImpl")
public class WeeklyPlanSectionServiceImpl extends BaseServiceImpl<WeeklyPlanSection, Long> implements
        WeeklyPlanSectionService
{

    @Resource(name = "weeklyPlanSectionDaoImpl")
    private WeeklyPlanSectionDao weeklyPlanSectionDao;

    @Resource(name = "weeklyPlanSectionDaoImpl")
    public void setBaseDao(WeeklyPlanSectionDao weeklyPlanSectionDao)
    {
        super.setBaseDao(weeklyPlanSectionDao);
    }

    @Override
    public List<WeeklyPlanSection> findByClassAndSchoolYear(DictSchool dictSchool, SchoolYearMng schoolYearMng,
            DictClass dictClass, List<Order> orders)
    {
        return weeklyPlanSectionDao.findByClassAndSchoolYear(dictSchool, schoolYearMng, dictClass, orders);
    }

    @Override
    public List<WeeklyPlanSection> findBySchool(DictSchool dictSchool)
    {
        // TODO Auto-generated method stub
        return weeklyPlanSectionDao.findBySchool(dictSchool);
    }

    @Override
    public List<WeeklyPlanSection> findByClass(DictClass dictClass)
    {
        // TODO Auto-generated method stub
        return weeklyPlanSectionDao.findByClass(dictClass);
    }

    @Override
    public List<WeeklyPlanSection> findBySchoolYearMng(SchoolYearMng schoolYearMng)
    {
        // TODO Auto-generated method stub
        return weeklyPlanSectionDao.findBySchoolYearMng(schoolYearMng);
    }

    @Override
    public void changeIsCurrent()
    {
        // TODO Auto-generated method stub
        List<WeeklyPlanSection> sections = this.findAll();
        if (sections == null || sections.size() == 0)
        {
            return;
        }
        for (WeeklyPlanSection section : sections)
        {
            Date date = new Date();
            Date startDate = section.getWeekStartDate();
            Date endDate = section.getWeekEndDate();
            if (date.after(startDate) && date.before(endDate))
            {
                section.setIsCurrent(true);
                this.update(section);
            }
            else
            {
                if ((section.getIsCurrent() == null) || (section.getIsCurrent()))
                {
                    section.setIsCurrent(false);
                    this.update(section);
                }
            }
        }
    }

}
