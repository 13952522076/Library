package com.sammyun.dao.impl.dict;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.dict.DictCourseNameDao;
import com.sammyun.entity.dict.DictCourseName;

/**
 * DictCourseName * DaoImpl - 课程名称字典表
 * 
 * @author Sencloud Team

 */
@Repository("dictCourseNameDaoImpl")
public class DictCourseNameDaoImpl extends BaseDaoImpl<DictCourseName, Long> implements DictCourseNameDao  {

}
