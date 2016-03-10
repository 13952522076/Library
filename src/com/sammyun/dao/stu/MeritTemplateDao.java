package com.sammyun.dao.stu;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.stu.MeritTemplate;

/**
 * MeritTemplate * Dao - 评价等级模板
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface MeritTemplateDao extends BaseDao<MeritTemplate, Long> 
{
    /**
     * 查找每个学校的评价模板
     * @param dictSchool
     * @return
     * @see [类、类#方法、类#成员]
     */
   public List<MeritTemplate> findBySchool(DictSchool dictSchool);
}
