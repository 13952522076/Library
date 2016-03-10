/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.sammyun.entity.BaseEntity;

/**
 * Listener - 创建日期、修改日期处理
 * 
 * @author Sencloud Team

 */
public class EntityListener
{

    /**
     * 保存前处理
     * 
     * @param entity 基类
     */
    @PrePersist
    public void prePersist(BaseEntity entity)
    {
        entity.setCreateDate(new Date());
        entity.setModifyDate(new Date());
    }

    /**
     * 更新前处理
     * 
     * @param entity 基类
     */
    @PreUpdate
    public void preUpdate(BaseEntity entity)
    {
        entity.setModifyDate(new Date());
    }

}
