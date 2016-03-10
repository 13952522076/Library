package com.sammyun.dao.impl.attendance;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.attendance.AttendanceEquipmentDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.attendance.AttendanceEquipment;

/**
 * Dao - 考勤机
 * 
 * @author xutianlong
 * @version [版本号, Apr 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository("attendanceEquipmentDaoImpl")
public class AttendanceEquipmentDaoImpl extends BaseDaoImpl<AttendanceEquipment, Long> implements
        AttendanceEquipmentDao
{

    /**
     * 判断考勤机序列号是否唯一 <功能详细描述>
     * 
     * @param equipmentSequence
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean sequenceExsit(String equipmentSequence)
    {
        if (equipmentSequence == null)
        {
            return false;
        }
        String jpql = "select count(*) from AttendanceEquipment attendanceEquipment where lower(attendanceEquipment.equipmentSequence) = lower(:equipmentSequence)";
        Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "equipmentSequence", equipmentSequence).getSingleResult();
        return count > 0;
    }

    @Override
    public AttendanceEquipment findByEquipmentSequence(String equipmentSequence)
    {
        if (equipmentSequence == null)
        {
            return null;
        }
        String jpql = "select attendanceEquipment from AttendanceEquipment attendanceEquipment where attendanceEquipment.equipmentSequence = :equipmentSequence";
        try
        {
            return entityManager.createQuery(jpql, AttendanceEquipment.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                    "equipmentSequence", equipmentSequence).getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

}
