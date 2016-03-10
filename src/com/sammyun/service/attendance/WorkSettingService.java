package com.sammyun.service.attendance;

import java.util.List;

import com.sammyun.entity.attendance.WorkSetting;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.BaseService;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

/**
 * Service - 班次设置
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface WorkSettingService extends BaseService<WorkSetting, Long>
{
    /**
     * 根据学校查找出学校下的所有班次
     * <功能详细描述>
     * @param dictSchool
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<WorkSetting> findBySchool(DictSchool dictSchool);
    
    /**
     * 根据学校查找出学校下的默认班次
     * @param dictSchool
     * @param isDefalut
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<WorkSetting> findBySchool(DictSchool dictSchool,Boolean isDefalut);
}
