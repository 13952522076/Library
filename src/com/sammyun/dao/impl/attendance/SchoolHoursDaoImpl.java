package com.sammyun.dao.impl.attendance;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.attendance.SchoolHoursDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.attendance.SchoolHours;

/**
 * DaoImpl - 上学放学时间设置
 * 
 * @author Sencloud Team

 */
@Repository("schoolHoursDaoImpl")
public class SchoolHoursDaoImpl extends BaseDaoImpl<SchoolHours, Long> implements SchoolHoursDao
{

}
