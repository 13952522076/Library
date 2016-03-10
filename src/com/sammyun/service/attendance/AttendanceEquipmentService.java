package com.sammyun.service.attendance;

import com.sammyun.entity.attendance.AttendanceEquipment;
import com.sammyun.entity.attendance.TimeCard;
import com.sammyun.service.BaseService;

/**
 * Service - 考勤机
 * 
 * @author xutianlong
 * @version [版本号, Apr 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface AttendanceEquipmentService extends BaseService<AttendanceEquipment, Long>
{
    /**
     * 判断考勤机序列号是否唯一
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
