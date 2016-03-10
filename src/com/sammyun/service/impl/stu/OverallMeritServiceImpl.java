package com.sammyun.service.impl.stu;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.stu.OverallMeritDao;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.stu.OverallMerit;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.stu.OverallMeritService;

/**
 * Service   - 综合评价
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("overallMeritServiceImpl")
public class OverallMeritServiceImpl extends BaseServiceImpl<OverallMerit, Long> implements OverallMeritService
{
    @Resource(name = "overallMeritDaoImpl")
    private OverallMeritDao overallMeritDao;

    @Resource(name = "overallMeritDaoImpl")
    public void setBaseDao(OverallMeritDao overallMeritDao)
    {
        super.setBaseDao(overallMeritDao);
    }

    @Override
    public Page<OverallMerit> findBySchool(DictSchool dictSchool,Pageable pageable)
    {
        return overallMeritDao.findBySchool(dictSchool,pageable);
    }

    @Override
    public List<OverallMerit> findByDictStudent(DictStudent dictStudent)
    {
        return overallMeritDao.findByDictStudent(dictStudent);
    }

    @Override
    public void deleteByDictStudent(DictStudent dictStudent)
    {
        overallMeritDao.deleteByDictStudent(dictStudent);
    }
}
