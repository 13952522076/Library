/*
 * Copyright 2012-2014 sencloud.com.cn. All rights reserved.
 * Support: http://www.sencloud.com.cn
 * License: http://www.sencloud.com.cn/license
 */
package com.sammyun.service.impl.app;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.app.AppCategoryDao;
import com.sammyun.entity.app.AppCategory;
import com.sammyun.service.app.AppCategoryService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * Service - 应用分类
 * 
 * @author tangchao
 * @version 1.0
 */
@Service("appCategoryServiceImpl")
public class AppCategoryServiceImpl extends BaseServiceImpl<AppCategory, Long> implements AppCategoryService
{

	@Resource(name = "appCategoryDaoImpl")
    private AppCategoryDao appCategoryDao;
	
    @Resource(name = "appCategoryDaoImpl")
    public void setBaseDao(AppCategoryDao appCategoryDao)
    {
        super.setBaseDao(appCategoryDao);
    }
    
    /**
	  *   判断分类名称是否唯一
	  * @param previousName
	  * 	原名称
	  * @param currentName
	  * 	当前名称
	  * @return
	  */
    @Transactional(readOnly = true)
	public boolean nameUnique(String previousName, String currentName) {
		if (StringUtils.equalsIgnoreCase(previousName, currentName)) {
			return true;
		} else {
			if (appCategoryDao.nameUnique(currentName)) {
				return false;
			} else {
				return true;
			}
		}
	}
    
    @Override
	public Page<AppCategory> findPage(Boolean status, Pageable pageable){
    	return appCategoryDao.findPage(status, pageable);
    }
    
    @Override
	public List<AppCategory> findList(Boolean status){
    	return appCategoryDao.findList(status);
    }
}
