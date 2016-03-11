package com.sammyun.service.impl.news;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.news.NewsDao;
import com.sammyun.entity.news.News;
import com.sammyun.entity.news.NewsCategory;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.news.NewsService;

/**
 * News * ServiceImpl - 新闻数据
 * 


 */
@Service("newsServiceImpl")
public class NewsServiceImpl extends BaseServiceImpl<News, Long> implements NewsService {

    @Resource(name = "newsDaoImpl")
    private NewsDao newsDao;

    @Resource(name = "newsDaoImpl")
    public void setBaseDao(NewsDao newsDao){
        super.setBaseDao(newsDao);
    }

    @Override
    public Page<News> findBySchool(NewsCategory newsCategory, Integer status,Pageable pageable)
    {
        return newsDao.findBySchool(newsCategory, status, pageable);
    }


}
