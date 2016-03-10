package com.sammyun.service.news;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.entity.news.News;
import com.sammyun.entity.news.NewsCategory;
import com.sammyun.service.BaseService;

/**
 * News * Service - 新闻数据
 * 
 * @author Sencloud Team

 */
public interface NewsService extends BaseService<News, Long> {

    /**
     * 根据新闻类别查询
     */
    public Page<News> findBySchool(NewsCategory newsCategory,Integer status,Pageable pageable);
}
