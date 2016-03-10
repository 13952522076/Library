package com.sammyun.dao.course;

import java.util.List;

import com.sammyun.Order;
import com.sammyun.dao.BaseDao;
import com.sammyun.entity.course.QualityCourse;
import com.sammyun.entity.dict.DictSchool;

/**
 * Dao - 精品课程
 * 
 */
public interface QualityCourseDao extends BaseDao<QualityCourse, Long> 
{
    /**
     * 根据学校查询精品课程
     */
    public List<QualityCourse> findBySchool(DictSchool dictSchool,Long status, List<Order> orders);
}
