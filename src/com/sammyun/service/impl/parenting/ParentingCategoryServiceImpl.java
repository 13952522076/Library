package com.sammyun.service.impl.parenting;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.Order;
import com.sammyun.dao.parenting.ParentingCategoryDao;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.parenting.ParentingCategory;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.parenting.ParentingCategoryService;

/**
 * ParentingCategory * ServiceImpl - 育儿类别
 * 
 * @author Sencloud Team

 */
@Service("parentingCategoryServiceImpl")
public class ParentingCategoryServiceImpl extends BaseServiceImpl<ParentingCategory, Long> implements
        ParentingCategoryService
{
    @Resource(name = "parentingCategoryDaoImpl")
    private ParentingCategoryDao parentingCategoryDao;

    @Resource(name = "parentingCategoryDaoImpl")
    public void setBaseDao(ParentingCategoryDao parentingCategoryDao)
    {
        super.setBaseDao(parentingCategoryDao);
    }

    @Override
    public List<ParentingCategory> findBySchool(DictSchool dictSchool, Integer defFlag, Integer status,
            List<Order> orders)
    {
        return parentingCategoryDao.findBySchool(dictSchool, defFlag,status,orders);
    }
    
}
