package com.sammyun.service.impl.stu;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.stu.StudentWorksDao;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.stu.StudentWorks;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.stu.StudentWorksService;

/**
 * ServiceImpl - 学生作品
 */
@Service("studentWorksServiceImpl")
public class StudentWorksServiceImpl extends BaseServiceImpl<StudentWorks, Long> implements StudentWorksService
{

    @Resource(name = "studentWorksDaoImpl")
    private StudentWorksDao studentWorksDao;

    @Resource(name = "studentWorksDaoImpl")
    public void setBaseDao(StudentWorksDao studentWorksDao)
    {
        super.setBaseDao(studentWorksDao);
    }

    @Override
    public List<StudentWorks> getListByDictStudent(DictStudent dictStudent)
    {
        // TODO Auto-generated method stub
        return studentWorksDao.getListByDictStudent(dictStudent);
    }

    @Override
    public void deleteByDictStudent(DictStudent dictStudent)
    {
        // TODO Auto-generated method stub
        studentWorksDao.deleteByDictStudent(dictStudent);
    }

}
