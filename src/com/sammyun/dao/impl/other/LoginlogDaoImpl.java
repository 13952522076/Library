package com.sammyun.dao.impl.other;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.other.LoginlogDao;
import com.sammyun.entity.other.Loginlog;

/**
 * Loginlog * DaoImpl - 登录日志
 * 


 */
@Repository("loginlogDaoImpl")
public class LoginlogDaoImpl extends BaseDaoImpl<Loginlog, Long> implements LoginlogDao  {

}
