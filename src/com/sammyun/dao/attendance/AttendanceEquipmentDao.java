/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao.attendance;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.attendance.AttendanceEquipment;

/**
 * Dao - 考勤机
 * 


 */
public interface AttendanceEquipmentDao extends BaseDao<AttendanceEquipment, Long>
{
    /**
     * 检查考勤机序列号是否唯一
     * <功能详细描述>
     * @param equipmentSequence
     * @return
     * @see [类、类#方法、类#成员]
     */
    boolean sequenceExsit(String equipmentSequence);
    
    /**
     * 通过设备号查询设备信息
     * @param equipmentSequence
     * @return
     * @see [类、类#方法、类#成员]
     */
    public AttendanceEquipment findByEquipmentSequence(String equipmentSequence);
}
