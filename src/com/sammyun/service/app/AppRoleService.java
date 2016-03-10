package com.sammyun.service.app;

import java.util.List;

import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.app.AppRole;
import com.sammyun.service.BaseService;

/**
 * Service - 应用角色
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface AppRoleService extends BaseService<AppRole, Long> {
	
	/**
	 * 根据条件查询应用角色
	 * @param memberTypes
	 * @return
	 */
	public List<AppRole> findList(List<MemberType> memberTypes);

}