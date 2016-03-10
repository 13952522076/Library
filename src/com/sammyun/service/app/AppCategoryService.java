/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service.app;

import java.util.List;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.entity.app.AppCategory;
import com.sammyun.service.BaseService;

/**
 * Service - 应用分类
 * @author tangchao
 * @version 1.0
 *
 */
public interface AppCategoryService extends BaseService<AppCategory, Long>
{
	 /**
	  *   判断分类名称是否唯一
	  * @param previousName
	  * 	原名称
	  * @param currentName
	  * 	当前名称
	  * @return
	  */
    boolean nameUnique(String previousName, String currentName);
    
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
