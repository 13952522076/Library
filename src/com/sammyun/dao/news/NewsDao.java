package com.sammyun.dao.news;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.BaseDao;
import com.sammyun.entity.news.News;
import com.sammyun.entity.news.NewsCategory;

/**
 * News * Dao - 新闻数据
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface NewsDao extends BaseDao<News, Long> {
    
    /**
     * 根据新闻类别查询
     */
    public Page<News> findBySchool(NewsCategory newsCategory,Integer status,Pageable pageable);

}
