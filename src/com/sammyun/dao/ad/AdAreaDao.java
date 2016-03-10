package com.sammyun.dao.ad;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.ad.AdArea;

/**
 *  Dao - 投放 区域（定向地区）
 * 
 * @author chenyunfeng
 * @version [版本号, Aug 28, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface AdAreaDao extends BaseDao<AdArea, Long>
{
    /**
     * 根据全称查找投放区域
     * <功能详细描述>
     * @param fullName
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<AdArea> finListByFullName(String fullName);
}
