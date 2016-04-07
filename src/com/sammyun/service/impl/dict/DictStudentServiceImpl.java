package com.sammyun.service.impl.dict;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sammyun.dao.dict.DictClassDao;
import com.sammyun.dao.dict.DictStudentDao;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.service.dict.DictStudentService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * DictStudent * ServiceImpl - 学生
 */
@Service("dictStudentServiceImpl")
public class DictStudentServiceImpl extends BaseServiceImpl<DictStudent, Long> implements DictStudentService
{

    @Resource(name = "dictStudentDaoImpl")
    private DictStudentDao dictStudentDao;

    @Resource(name = "dictClassDaoImpl")
    private DictClassDao dictClassDao;

    @Resource(name = "dictStudentDaoImpl")
    public void setBaseDao(DictStudentDao dictStudentDao)
    {
        super.setBaseDao(dictStudentDao);
    }

    @Override
    public List<DictStudent> getDictStudentByClassId(String dictClassId)
    {
        // TODO Auto-generated method stub
        if (dictClassId == null)
        {
            return null;
        }
        long classId = Long.parseLong(dictClassId);
        DictClass dictClass = dictClassDao.find(classId);
        if (dictClass == null)
        {
            return null;
        }
        List<DictStudent> dictStudentList = dictStudentDao.getStudentByClass(dictClass);
        return dictStudentList;
    }

    @Override
    public List<DictStudent> findByStudentNo(String studentNo, DictSchool dictSchool)
    {
        return dictStudentDao.findByStudentNo(studentNo, dictSchool);
    }

    @Override
    public List<DictStudent> findStudentsBySchool(DictSchool dictSchool)
    {
        return dictStudentDao.findStudentsBySchool(dictSchool);
    }

    @Override
    public boolean studentNoUnique(String previousStudentNo, String studentNo, DictSchool dictSchool)
    {
        if (StringUtils.equalsIgnoreCase(previousStudentNo, studentNo))
        {
            return true;
        }
        else
        {
            if (dictStudentDao.studentNoUnique(studentNo, dictSchool))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    @Override
    public DictStudent findByStudentNoSingle(String studentNo, DictSchool dictSchool)
    {
        return dictStudentDao.findByStudentNoSingle(studentNo, dictSchool);
    }

}
