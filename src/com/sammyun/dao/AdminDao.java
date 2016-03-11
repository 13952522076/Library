/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao;

import com.sammyun.entity.Admin;
import com.sammyun.entity.dict.DictSchool;

/**
 * Dao - 管理员
 * 


 */
public interface AdminDao extends BaseDao<Admin, Long>
{

    /**
     * 判断用户名是否存在
     * 
     * @param username 用户名(忽略大小写)
     * @return 用户名是否存在
     */
    boolean usernameExists(String username);

    /**
     * 根据用户名查找管理员
     * 
     * @param username 用户名(忽略大小写)
     * @return 管理员，若不存在则返回null
     */
    Admin findByUsername(String username);
    
    /**
     * 根据管理员获取相关学校
     * 
     * @param admin
     * @return DictSchool
     */
    DictSchool getSchoolByAdmin(Admin admin);

}
