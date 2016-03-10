package com.sammyun.service.ad;

import java.util.List;

import com.sammyun.entity.ad.AdCategory;
import com.sammyun.service.BaseService;

/**
 *  Service - 广告分类
 * 
 * @author chenyunfeng
 * @version [版本号, Aug 28, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface AdCategoryService extends BaseService<AdCategory, Long>
{
    /**
     * 根据名称查询出类别
     * <功能详细描述>
     * @param name
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<AdCategory> findList(String name);
}
