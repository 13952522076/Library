package com.sammyun.service.impl.parenting;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.parenting.ParentingDao;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.parenting.Parenting;
import com.sammyun.entity.parenting.ParentingCategory;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.parenting.ParentingService;

/**
 * Parenting * ServiceImpl - 育儿数据
 * 
 * @author Sencloud Team

 */
@Service("parentingServiceImpl")
public class ParentingServiceImpl extends BaseServiceImpl<Parenting, Long> implements ParentingService
{

    @Resource(name = "parentingDaoImpl")
    private ParentingDao parentingDao;

    @Resource(name = "parentingDaoImpl")
    public void setBaseDao(ParentingDao parentingDao)
    {
        super.setBaseDao(parentingDao);
    }

    @Override
    public Long findIsTopCountByCategory(ParentingCategory parentingCategory,DictSchool dictSchool)
    {
        return parentingDao.findIsTopCountByCategory(parentingCategory, dictSchool);
    }

    @Override
    public List<Parenting> findBySchool(DictSchool dictSchool,Boolean isTop,Integer status,Integer categoryDefFlag,Integer categoryStatus)
    {
        return parentingDao.findBySchool(dictSchool,isTop,status,categoryDefFlag,categoryStatus);
    }

    @Override
    public Page<Parenting> findByCategory(ParentingCategory parentingCategory,Boolean isTop,Integer status,Pageable pageable)
    {
        return parentingDao.findByCategory(parentingCategory, isTop, status, pageable);
    }

    @Override
    public Long findIsTopCount()
    {
        // TODO Auto-generated method stub
        return parentingDao.findIsTopCount();
    }

    @Override
    public List<Parenting> findByCategory(ParentingCategory parentingCategory,Boolean isTop)
    {
        // TODO Auto-generated method stub
        return parentingDao.findByCategory(parentingCategory,isTop);
    }
}
