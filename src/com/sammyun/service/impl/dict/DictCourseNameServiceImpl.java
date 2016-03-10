package com.sammyun.service.impl.dict;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.dict.DictCourseNameService;

import com.sammyun.dao.dict.DictCourseNameDao;
import com.sammyun.entity.dict.DictCourseName;

/**
 * DictCourseName * ServiceImpl - 课程名称字典表
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("dictCourseNameServiceImpl")
public class DictCourseNameServiceImpl extends BaseServiceImpl<DictCourseName, Long> implements DictCourseNameService {

    @Resource(name = "dictCourseNameDaoImpl")
    private DictCourseNameDao dictCourseNameDao;

    @Resource(name = "dictCourseNameDaoImpl")
    public void setBaseDao(DictCourseNameDao dictCourseNameDao){
        super.setBaseDao(dictCourseNameDao);
    }


}
