/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service.impl;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.sf.ehcache.CacheManager;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sammyun.service.SearchService;

/**
 * Service - 搜索
 * 


 */
@Service("searchServiceImpl")
@Transactional
public class SearchServiceImpl implements SearchService
{

    @PersistenceContext
    protected EntityManager entityManager;

    @Resource(name = "ehCacheManager")
    private CacheManager cacheManager;

    @SuppressWarnings("unused")
    public void index(Class<?> type)
    {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

    }

    @Override
    public void index()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void purge()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void purge(Class<?> type)
    {
        // TODO Auto-generated method stub

    }
}
