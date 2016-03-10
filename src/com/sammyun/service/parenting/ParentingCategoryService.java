package com.sammyun.service.parenting;

import java.util.List;

import com.sammyun.Order;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.parenting.ParentingCategory;
import com.sammyun.service.BaseService;

/**
 * ParentingCategory * Service - 育儿类别
 * 
 * @author Sencloud Team

 */
public interface ParentingCategoryService extends BaseService<ParentingCategory, Long>
{

    /**
     * 根据学校查询育儿类别
     */
    public List<ParentingCategory> findBySchool(DictSchool dictSchool, Integer defFlag, Integer status, List<Order> orders);
}
