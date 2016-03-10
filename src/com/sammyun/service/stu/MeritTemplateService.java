package com.sammyun.service.stu;

import java.util.List;

import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.stu.MeritTemplate;
import com.sammyun.service.BaseService;

/**
 * MeritTemplate * Service - 评价等级模板
 * 
 * @author Sencloud Team

 */
public interface MeritTemplateService  extends BaseService<MeritTemplate, Long>
{
    /**
     * 查找每个学校的评价模板
     * @param dictSchool
     * @return
     * @see [类、类#方法、类#成员]
     */
   public List<MeritTemplate> findBySchool(DictSchool dictSchool);
}
