package com.sammyun.dao.impl.gd;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.gd.AtUserDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.gd.AtUser;

/**
 * AtUser * DaoImpl - 成长记AT用户
 * 
 * @author Sencloud Team

 */
@Repository("atUserDaoImpl")
public class AtUserDaoImpl extends BaseDaoImpl<AtUser, Long> implements AtUserDao
{

}
