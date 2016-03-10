package com.sammyun.dao.news;

import java.util.List;

import com.sammyun.Order;
import com.sammyun.dao.BaseDao;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.news.NewsCategory;

public interface NewsCategoryDao extends BaseDao<NewsCategory, Long>
{
    /**
     * 根据学校查询新闻类别
     */
    public List<NewsCategory> findBySchool(DictSchool dictSchool,Integer defFlag,Integer status,List<Order> orders);

}
