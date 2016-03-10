/*

 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.ExcelDao;
import com.sammyun.entity.ExcelMessage;
import com.sammyun.entity.ExcelMessage.Status;
import com.sammyun.entity.Member;
import com.sammyun.entity.dict.DictStudent;

/**
 * Dao - excel导入
 * 
 * @author Sencloud Team

 */
@Repository("excelDaoImpl")
public class ExcelDaoImpl implements ExcelDao
{

    /**
     * 判断自身获得返回信息 <功能详细描述>
     * 
     * @param members
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ExcelMessage validateMemberSelf(List<Member> members)
    {
        ExcelMessage ret = new ExcelMessage();
        ret.setStatus(Status.fail);
        if (members == null)
        {
            ret.setError("获取的列表为空");
            return ret;
        }
        Member one = null;
        Member two = null;
        for (int i = 0; i < members.size() - 1; i++)
        {
            one = members.get(i);
            for (int j = i + 1; j < members.size(); j++)
            {
                two = members.get(j);
                if((one.getUsername()!=null)||(two.getUsername()!=null)){
                    if (one.getUsername().equals(two.getUsername()))
                    {
                        ret.setError("第" + (i + 1) + "条数据与第" + (j + 1) + "条纪录用户名重复");
                        return ret;
                    }
                }
                if((one.getMobile()!=null)||(two.getMobile()!=null)){
                    if (one.getMobile().equals(two.getMobile()))
                    {
                        ret.setError("第" + (i + 1) + "条数据与第" + (j + 1) + "条纪录手机号重复");
                        return ret;
                    }
                }
                if((one.getIdCard()!=null)||(two.getIdCard()!=null)){
                    if (one.getIdCard().equals(two.getIdCard()))
                    {
                        ret.setError("第" + (i + 1) + "条数据与第" + (j + 1) + "条纪录身份证重复");
                        return ret;
                    }
                }
                if((one.getEmail()!=null)||(two.getEmail()!=null)){
                    if (one.getEmail().equals(two.getEmail()))
                    {
                        ret.setError("第" + (i + 1) + "条数据与第" + (j + 1) + "条纪录邮箱重复");
                        return ret;
                    }
                }
            }
        }
        ret.setStatus(Status.success);
        return ret;
    }

	@Override
	public ExcelMessage validateStudentSelf(List<DictStudent> dictStudents) {
		ExcelMessage ret = new ExcelMessage();
		ret.setStatus(Status.fail);
		if (dictStudents == null)
        {
            ret.setError("获取的列表为空");
            return ret;
        }
		DictStudent one = null;
		DictStudent two = null;
		for (int i = 0; i < dictStudents.size() - 1; i++){
			one = dictStudents.get(i);
			for (int j = i + 1; j < dictStudents.size(); j++){
				two = dictStudents.get(j);
				if((one.getStudentNo()!=null)||(two.getStudentNo()!=null)){
                    if (one.getStudentNo().equals(two.getStudentNo()))
                    {
                        ret.setError("第" + (i + 1) + "条数据与第" + (j + 1) + "条纪录学号重复");
                        return ret;
                    }
                }
			}
		}
		ret.setStatus(Status.success);
        return ret;
	}

}
