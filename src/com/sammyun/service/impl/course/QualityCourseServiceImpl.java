package com.sammyun.service.impl.course;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.Order;
import com.sammyun.dao.course.QualityCourseDao;
import com.sammyun.entity.course.QualityCourse;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.course.QualityCourseService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * QualityCourse * ServiceImpl - 精品课程
 * 
 * @author Sencloud Team
 * @version 3.0
 */

@Service("qualityCourseServiceImpl")
public class QualityCourseServiceImpl extends BaseServiceImpl<QualityCourse, Long> implements QualityCourseService
{

    @Resource(name = "qualityCourseDaoImpl")
    private QualityCourseDao qualityCourseDao;

    @Resource(name = "qualityCourseDaoImpl")
    public void setBaseDao(QualityCourseDao qualityCourseDao)
    {
        super.setBaseDao(qualityCourseDao);
    }
    
    @Override
    public List<QualityCourse> findBySchool(DictSchool dictSchool, Long status, List<Order> orders)
    {
        // TODO Auto-generated method stub
        return qualityCourseDao.findBySchool(dictSchool, status, orders);
    }

}
