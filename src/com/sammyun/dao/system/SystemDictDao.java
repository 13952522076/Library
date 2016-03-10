package com.sammyun.dao.system;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.system.SystemDict;

/**
 * Dao - 系统字典 <一句话功能简述> <功能详细描述>
 * 
 * @author xutianlong
 * @version [版本号, Jul 10, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface SystemDictDao extends BaseDao<SystemDict, Long>
{
    /**
     * 根据代码查询出字典列表
     * 
     * @param code
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<SystemDict> findByCode(String code);

    /**
     * 根据代码描述查询出字典列表
     * 
     * @param codeDesc
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<SystemDict> findByCodeDesc(String codeDesc);
}
