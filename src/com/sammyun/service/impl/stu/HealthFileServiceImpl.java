package com.sammyun.service.impl.stu;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sammyun.dao.stu.HealthFileDao;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.stu.HealthFile;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.impl.recipe.RecipeDetailServiceImpl;
import com.sammyun.service.stu.HealthFileService;

/**
 * ServiceImpl - 健康档案
 * 
 * @author Sencloud Team

 */
@Service("healthFileServiceImpl")
public class HealthFileServiceImpl extends BaseServiceImpl<HealthFile, Long> implements HealthFileService
{
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(RecipeDetailServiceImpl.class);

    @Resource(name = "healthFileDaoImpl")
    private HealthFileDao healthFileDao;

    @Resource(name = "healthFileDaoImpl")
    public void setBaseDao(HealthFileDao healthFileDao)
    {
        super.setBaseDao(healthFileDao);
    }

    @Override
    public HealthFile findByDictStudent(DictStudent dictStudent)
    {
        // TODO Auto-generated method stub
        return healthFileDao.findByDictStudent(dictStudent);
    }

}
