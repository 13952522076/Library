package com.sammyun.dao.parenting;

import java.util.List;

import com.sammyun.Order;
import com.sammyun.dao.BaseDao;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.parenting.ParentingCategory;

public interface ParentingCategoryDao extends BaseDao<ParentingCategory, Long>
{
    /**
     * 根据学校查询育儿类别
     */
    public List<ParentingCategory> findBySchool(DictSchool dictSchool, Integer defFlag, Integer status,
            List<Order> orders);

}
