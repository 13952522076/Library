package com.sammyun.service.impl.course;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.Order;
import com.sammyun.dao.course.SchoolYearMngDao;
import com.sammyun.entity.course.SchoolYearMng;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.course.SchoolYearMngService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * Service - 学年管理
 * 
 * @author xutianlong
 * @version [版本号, Apr 13, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("schoolYearMngServiceImpl")
public class SchoolYearMngServiceImpl extends BaseServiceImpl<SchoolYearMng, Long> implements SchoolYearMngService
{
    @Resource(name = "schoolYearMngDaoImpl")
    private SchoolYearMngDao schoolYearMngDao;

    @Resource(name = "schoolYearMngDaoImpl")
    public void setBaseDao(SchoolYearMngDao schoolYearMngDao)
    {
        super.setBaseDao(schoolYearMngDao);
    }

    /**
     * 根据学校查找出学年列表
     * <功能详细描述>
     * @param dictSchool
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<SchoolYearMng> findBySchool(DictSchool dictSchool)
    {
        List<SchoolYearMng> schoolYearMngs = schoolYearMngDao.findBySchool(dictSchool);
        return schoolYearMngs;
    }
    
    @Override
    public List<SchoolYearMng> findSchoolYearMng(DictSchool dictSchool,Boolean isShow,List<Order> orders)
    {
        return schoolYearMngDao.findSchoolYearMng(dictSchool,isShow,orders);
    }
}
