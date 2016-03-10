/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao;

import java.util.List;

import com.sammyun.entity.ExcelMessage;
import com.sammyun.entity.Member;
import com.sammyun.entity.dict.DictStudent;

/**
 * Dao - excel导入
 * 
 * @author Sencloud Team

 */
public interface ExcelDao
{   
    /**
     * 判断自身获得返回信息
     * <功能详细描述>
     * @param members
     * @return
     * @see [类、类#方法、类#成员]
     */
    ExcelMessage validateMemberSelf(List<Member> members);
    
    /**
     * 判断学生自身获得返回信息
     * <功能详细描述>
     * @param dictStudents
     * @return
     * @see [类、类#方法、类#成员]
     */
    ExcelMessage validateStudentSelf(List<DictStudent> dictStudents);
}
