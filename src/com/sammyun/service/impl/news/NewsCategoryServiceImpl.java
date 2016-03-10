package com.sammyun.service.impl.news;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.Order;
import com.sammyun.dao.news.NewsCategoryDao;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.news.NewsCategory;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.news.NewsCategoryService;

/**
 * NewsCategory * ServiceImpl - 新闻类别
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("newsCategoryServiceImpl")
public class NewsCategoryServiceImpl extends BaseServiceImpl<NewsCategory, Long> implements NewsCategoryService
{
    @Resource(name = "newsCategoryDaoImpl")
    private NewsCategoryDao newsCategoryDao;

    @Resource(name = "newsCategoryDaoImpl")
    public void setBaseDao(NewsCategoryDao newsCategoryDao){
        super.setBaseDao(newsCategoryDao);
    }

    @Override
    public List<NewsCategory> findBySchool(DictSchool dictSchool, Integer defFlag, Integer status,List<Order> orders)
    {
        return newsCategoryDao.findBySchool(dictSchool, defFlag, status,orders);
    }

}
