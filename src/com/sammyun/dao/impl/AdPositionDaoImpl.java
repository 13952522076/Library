/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao.impl;


import org.springframework.stereotype.Repository;

import com.sammyun.dao.AdPositionDao;
import com.sammyun.entity.AdPosition;

/**
 * Dao - 广告位
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("adPositionDaoImpl")
public class AdPositionDaoImpl extends BaseDaoImpl<AdPosition, Long> implements AdPositionDao {

}