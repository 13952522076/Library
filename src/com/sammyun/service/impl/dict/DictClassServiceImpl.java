package com.sammyun.service.impl.dict;

import java.util.List;

import com.sammyun.service.impl.BaseServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.service.dict.DictClassService;
import com.sammyun.dao.dict.DictClassDao;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;

/**
 * Service   - 班级
 * 
 * @author Sencloud Team

 */
@Service("dictClassServiceImpl")
public class DictClassServiceImpl extends BaseServiceImpl<DictClass, Long> implements DictClassService
{

    @Resource(name = "dictClassDaoImpl")
    private DictClassDao dictClassDao;

    @Resource(name = "dictClassDaoImpl")
    public void setBaseDao(DictClassDao dictClassDao)
    {
        super.setBaseDao(dictClassDao);
    }

    @Override
    public List<DictClass> getClassesBySchool(DictSchool school)
    {
        List<DictClass> dictClasses = dictClassDao.getClassesBySchool(school);
        return dictClasses;
    }

    @Override
    public boolean codeExists(String code,DictSchool dictSchool)
    {
        return dictClassDao.codeExists(code,dictSchool);
    }

    @Override
    public boolean classNameExists(String className,DictSchool dictSchool)
    {
        return dictClassDao.classNameExist(className,dictSchool);
    }
}
