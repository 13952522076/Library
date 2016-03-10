package com.sammyun.dao.impl.app;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.app.AppCredentialDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.app.AppCredential;

/**
 * DaoImpl - 应用API授权
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("appCredentialDaoImpl")
public class AppCredentialDaoImpl extends BaseDaoImpl<AppCredential, Long> implements AppCredentialDao
{
	
}
