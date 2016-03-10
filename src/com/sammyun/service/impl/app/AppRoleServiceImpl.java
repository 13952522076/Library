
package com.sammyun.service.impl.app;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.app.AppRoleDao;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.app.AppRole;
import com.sammyun.service.app.AppRoleService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * Service - 应用角色
 * 
 * @author Sencloud Team

 */
@Service("appRoleServiceImpl")
public class AppRoleServiceImpl extends BaseServiceImpl<AppRole, Long> implements AppRoleService {

	@Resource(name = "appRoleDaoImpl")
	public void setBaseDao(AppRoleDao appRoleDao) {
		super.setBaseDao(appRoleDao);
	}
	
	@Resource(name = "appRoleDaoImpl")
    private AppRoleDao appRoleDao;
	
	@Override
	public List<AppRole> findList(List<MemberType> memberTypes){
		return appRoleDao.findList(memberTypes);
	}
}