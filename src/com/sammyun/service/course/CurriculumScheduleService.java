package com.sammyun.service.course;

import java.util.List;

import com.sammyun.Order;
import com.sammyun.entity.course.CurriculumSchedule;
import com.sammyun.entity.course.SchoolYearMng;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.service.BaseService;

/**
 * Service - 课程表管理
 * 
 * @author  xutianlong
 * @version  [版本号, Apr 13, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface CurriculumScheduleService extends BaseService<CurriculumSchedule, Long>
{
    /**
     * 根据班级和校历查询列表 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<CurriculumSchedule> findByClassAndSchoolYear(SchoolYearMng schoolYearMng, DictClass dictClass,
            List<Order> orders);

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
    boolean courseExsit(SchoolYearMng schoolYearMng, DictClass dictClass, int week, String lessons);

}
