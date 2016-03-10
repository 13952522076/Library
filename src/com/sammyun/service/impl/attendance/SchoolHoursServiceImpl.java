package com.sammyun.service.impl.attendance;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.attendance.SchoolHoursDao;
import com.sammyun.entity.attendance.SchoolHours;
import com.sammyun.service.attendance.SchoolHoursService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * ServiceImpl - 上学放学时间设置
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("schoolHoursServiceImpl")
public class SchoolHoursServiceImpl  extends BaseServiceImpl<SchoolHours, Long> implements SchoolHoursService
{
    @Resource(name = "schoolHoursDaoImpl")
    private SchoolHoursDao schoolHoursDao;
    
    @Resource(name = "schoolHoursDaoImpl")
    public void setBaseDao(SchoolHoursDao schoolHoursDao){
        super.setBaseDao(schoolHoursDao);
    }
}
