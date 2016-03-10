package com.sammyun.service.impl.ad;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.ad.AdCategoryDao;
import com.sammyun.entity.ad.AdCategory;
import com.sammyun.service.ad.AdCategoryService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 *  ServiceImpl - 广告分类
 * 
 * @author chenyunfeng
 * @version [版本号, Aug 28, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("adCategoryServiceImpl")
public class AdCategoryServiceImpl extends BaseServiceImpl<AdCategory, Long> implements AdCategoryService
{
    @Resource(name = "adCategoryDaoImpl")
    private AdCategoryDao adCategoryDao;

    @Resource(name = "adCategoryDaoImpl")
    public void setBaseDao(AdCategoryDao adCategoryDao){
        super.setBaseDao(adCategoryDao);
    }

    @Override
    public List<AdCategory> findList(String name)
    {
        // TODO Auto-generated method stub
        return adCategoryDao.findList(name);
    }
}
