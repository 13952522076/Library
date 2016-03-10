package com.sammyun.service.course;

import java.util.List;

import com.sammyun.Order;
import com.sammyun.entity.course.SchoolYearMng;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.BaseService;

/**
 * Service - 学年管理
 * 
 * @author xutianlong
 * @version [版本号, Apr 13, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface SchoolYearMngService extends BaseService<SchoolYearMng, Long>
{
    /**
     * 根据学校查找出学年列表
     * <功能详细描述>
     * @param dictSchool
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<SchoolYearMng> findBySchool(DictSchool dictSchool);
    
    /**
     * 根据根据学校查询学年
     * 
     * @param member
     */
    public List<SchoolYearMng> findSchoolYearMng(DictSchool dictSchool,Boolean isShow,List<Order> orders );
    
}
