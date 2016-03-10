package com.sammyun.service.impl.attendance;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.attendance.WorkSettingDao;
import com.sammyun.entity.attendance.WorkSetting;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.attendance.WorkSettingService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * ServiceImpl - 班次设置
 * 
 * @author Sencloud Team

 */
@Service("workSettingServiceImpl")
public class WorkSettingServiceImpl extends BaseServiceImpl<WorkSetting, Long> implements WorkSettingService
{
    @Resource(name = "workSettingDaoImpl")
    private WorkSettingDao workSettingDao;
    
    @Resource(name = "workSettingDaoImpl")
    public void setBaseDao(WorkSettingDao workSettingDao){
        super.setBaseDao(workSettingDao);
    }

    @Override
    public List<WorkSetting> findBySchool(DictSchool dictSchool)
    {
        // TODO Auto-generated method stub
        return workSettingDao.findBySchool(dictSchool);
    }

    @Override
    public List<WorkSetting> findBySchool(DictSchool dictSchool, Boolean isDefalut)
    {
        return workSettingDao.findBySchool(dictSchool, isDefalut);
    }
}
