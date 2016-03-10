package com.sammyun.dao.attendance;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.attendance.WorkSetting;
import com.sammyun.entity.dict.DictSchool;

/**
 * Dao - 班次设置
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface WorkSettingDao extends BaseDao<WorkSetting, Long>
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
