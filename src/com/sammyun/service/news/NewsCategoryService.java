package com.sammyun.service.news;

import java.util.List;

import com.sammyun.Order;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.news.NewsCategory;
import com.sammyun.service.BaseService;

/**
 * NewsCategory * Service - 新闻类别
 * 
 * @author Sencloud Team

 */
public interface NewsCategoryService extends BaseService<NewsCategory, Long> 
{

    /**
     * 根据学校查询新闻类别
     */
    public List<NewsCategory> findBySchool(DictSchool dictSchool,Integer defFlag,Integer status,List<Order> orders);
}
