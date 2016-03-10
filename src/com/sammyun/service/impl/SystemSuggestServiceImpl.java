package com.sammyun.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.SystemSuggestDao;
import com.sammyun.entity.SystemSuggest;
import com.sammyun.service.SystemSuggestService;

@Service("systemSuggestServiceImpl")
public class SystemSuggestServiceImpl extends BaseServiceImpl<SystemSuggest, Long> implements SystemSuggestService
{
    @Resource(name = "systemSuggestDaoImpl")
    private SystemSuggestDao systemSuggestDao;

    @Resource(name = "systemSuggestDaoImpl")
    private void setBaseDao(SystemSuggestDao systemSuggestDao)
    {
        super.setBaseDao(systemSuggestDao);
    }

    @Override
    public Page<SystemSuggest> findByRealName(String realName, Pageable pageable)
    {
        // TODO Auto-generated method stub
        return systemSuggestDao.findByRealName(realName, pageable);
    }
}
