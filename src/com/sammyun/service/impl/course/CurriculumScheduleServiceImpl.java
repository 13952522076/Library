package com.sammyun.service.impl.course;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.Order;
import com.sammyun.dao.course.CurriculumScheduleDao;
import com.sammyun.entity.course.CurriculumSchedule;
import com.sammyun.entity.course.SchoolYearMng;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.service.course.CurriculumScheduleService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * Service - 课程管理
 * 
 * @author xutianlong
 * @version [版本号, Apr 13, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("curriculumScheduleServiceImpl")
public class CurriculumScheduleServiceImpl extends BaseServiceImpl<CurriculumSchedule, Long> implements
        CurriculumScheduleService
{
    @Resource(name = "curriculumScheduleDaoImpl")
    private CurriculumScheduleDao curriculumScheduleDao;

    @Resource(name = "curriculumScheduleDaoImpl")
    public void setBaseDao(CurriculumScheduleDao curriculumScheduleDao)
    {
        super.setBaseDao(curriculumScheduleDao);
    }

    /**
     * 根据班级和校历查询列表 <功能详细描述>
     * 
     * @param SchoolYearMng,DictClass
     * @return List<CurriculumSchedule>
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<CurriculumSchedule> findByClassAndSchoolYear(SchoolYearMng schoolYearMng, DictClass dictClass,
            List<Order> orders)
    {
        List<CurriculumSchedule> curriculumSchedules = curriculumScheduleDao.findByClassAndSchoolYear(schoolYearMng,
                dictClass, orders);
        // 对week，lessons相同的取createTime最新的一个
        if ((curriculumSchedules != null) && curriculumSchedules.size() > 1)
        {
            for (int i = 0; i < curriculumSchedules.size() - 1; i++)
            {
                CurriculumSchedule courseI = curriculumSchedules.get(i);
                for (int j = i + 1; j < curriculumSchedules.size(); j++)
                {
                    CurriculumSchedule courseJ = curriculumSchedules.get(j);
                    if (courseI.getLessons().equalsIgnoreCase(courseJ.getLessons())
                            && courseI.getWeek().equals(courseJ.getWeek()))
                    {
                        if (courseI.getCreateDate().before(courseJ.getCreateDate()))
                        {
                            // 删除是否先不做考虑
                            curriculumSchedules.remove(courseI);
                        }
                        else
                        {
                            // 删除是否先不做考虑
                            curriculumSchedules.remove(courseJ);
                        }
                    }
                }
            }
        }

        return curriculumSchedules;
    }

    /**
     * 根据校历，班级，星期，课节查询是否存在课程 <功能详细描述>
     * 
     * @param schoolYearMng
     * @param dictClass
     * @param week
     * @param lessons
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean courseExsit(SchoolYearMng schoolYearMng, DictClass dictClass, int week, String lessons)
    {
        // TODO Auto-generated method stub
        return curriculumScheduleDao.courseExsit(schoolYearMng, dictClass, week, lessons);
    }

}
