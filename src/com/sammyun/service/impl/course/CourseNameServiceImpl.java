package com.sammyun.service.impl.course;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.course.CourseNameDao;
import com.sammyun.entity.course.CourseName;
import com.sammyun.service.course.CourseNameService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * Service - 课程管理
 * 
 * @author xutianlong
 * @version [版本号, Apr 13, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("courseNameServiceImpl")
public class CourseNameServiceImpl extends BaseServiceImpl<CourseName, Long> implements CourseNameService
{
    @Resource(name = "courseNameDaoImpl")
    private CourseNameDao courseNameDao;

    @Resource(name = "courseNameDaoImpl")
    public void setBaseDao(CourseNameDao courseNameDao)
    {
        super.setBaseDao(courseNameDao);
    }
}
