package com.sammyun.service.impl.attendance;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.attendance.AttendanceEquipmentDao;
import com.sammyun.entity.attendance.AttendanceEquipment;
import com.sammyun.service.attendance.AttendanceEquipmentService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * Service - 考勤机
 * 
 * @author xutianlong
 * @version [版本号, Apr 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("attendanceEquipmentServiceImpl")
public class AttendanceEquipmentServiceImpl extends BaseServiceImpl<AttendanceEquipment, Long> implements
        AttendanceEquipmentService
{
    @Resource(name = "attendanceEquipmentDaoImpl")
    private AttendanceEquipmentDao attendanceEquipmentDao;

    @Resource(name = "attendanceEquipmentDaoImpl")
    public void setBaseDao(AttendanceEquipmentDao attendanceEquipmentDao)
    {
        super.setBaseDao(attendanceEquipmentDao);
    }

    /**
     * 判断考勤机序列号是否唯一
     * <功能详细描述>
     * @param equipmentSequence
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean sequenceExsit(String equipmentSequence)
    {
        // TODO Auto-generated method stub
        return attendanceEquipmentDao.sequenceExsit(equipmentSequence);
    }

    @Override
    public AttendanceEquipment findByEquipmentSequence(String equipmentSequence)
    {
        return attendanceEquipmentDao.findByEquipmentSequence(equipmentSequence);
    }
}
