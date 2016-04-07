package com.sammyun.service.system;

import java.util.List;

import com.sammyun.entity.system.SystemDict;
import com.sammyun.service.BaseService;

/**
 * Service - 系统字典 <br />
 * 需要一次性加载到内存，作为每次检索的数据集合
 * 
 * @author xutianlong
 * @version [版本号, Jul 10, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface SystemDictService extends BaseService<SystemDict, Long>
{
    /**
     * 设置 系统字典到内存
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    void set();

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
