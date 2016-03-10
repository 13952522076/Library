/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao.app;

import java.util.List;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.BaseDao;
import com.sammyun.entity.app.AppCategory;

/**
 * Dao - 应用分类
 * 
 * @author tangchao
 * @version 1.0
 */
public interface AppCategoryDao extends BaseDao<AppCategory, Long>
{

	/**
     * 判断分类名称是否存在
     * 
     * @param name 分类名称(忽略大小写)
     * @return 分类名称是否存在
     */
    boolean nameUnique(String name);
    
    /**
     *  根据条件查找应用分类
     * @param status
     * 		是否禁用
     * @param pageable
     * 		分页信息
     * @return
     */
    Page<AppCategory> findPage(Boolean status, Pageable pageable);
    
    /**
     *  根据条件查找应用分类
     * @param status
     * 		是否禁用
     * @return
     */
    List<AppCategory> findList(Boolean status);
}
