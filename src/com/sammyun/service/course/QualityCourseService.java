package com.sammyun.service.course;

import java.util.List;

import com.sammyun.Order;
import com.sammyun.entity.course.QualityCourse;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.BaseService;

/**
 * QualityCourse * Service - 精品课程
 * 
 * @author Sencloud Team

 */
public interface QualityCourseService extends BaseService<QualityCourse, Long>
{
    /**
     * 根据学校查询精品课程
     */
    public List<QualityCourse> findBySchool(DictSchool dictSchool,Long status, List<Order> orders);
}
