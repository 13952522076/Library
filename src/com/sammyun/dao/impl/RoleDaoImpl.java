/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao.impl;


import org.springframework.stereotype.Repository;

import com.sammyun.dao.RoleDao;
import com.sammyun.entity.Role;

/**
 * Dao - 角色
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("roleDaoImpl")
public class RoleDaoImpl extends BaseDaoImpl<Role, Long> implements RoleDao {

}