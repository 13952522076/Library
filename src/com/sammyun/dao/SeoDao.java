/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao;

import com.sammyun.entity.Seo;
import com.sammyun.entity.Seo.Type;

/**
 * Dao - SEO设置
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface SeoDao extends BaseDao<Seo, Long>
{

    /**
     * 查找SEO设置
     * 
     * @param type 类型
     * @return SEO设置
     */
    Seo find(Type type);

}
