package com.sammyun.service.impl.stu;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.stu.MeritTemplateDao;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.stu.MeritTemplate;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.stu.MeritTemplateService;

/**
 * Service   - 评价等级模板
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("meritTemplateServiceImpl")
public class MeritTemplateServiceImpl extends BaseServiceImpl<MeritTemplate, Long> implements MeritTemplateService
{
    @Resource(name = "meritTemplateDaoImpl")
    private MeritTemplateDao meritTemplateDao;

    @Resource(name = "meritTemplateDaoImpl")
    public void setBaseDao(MeritTemplateDao meritTemplateDao)
    {
        super.setBaseDao(meritTemplateDao);
    }

    @Override
    public List<MeritTemplate> findBySchool(DictSchool dictSchool)
    {
        return meritTemplateDao.findBySchool(dictSchool);
    }
}
