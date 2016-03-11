package com.sammyun.dao.app;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.app.AppRole;

/**
 * Dao - 应用角色
 * 


 */
public interface AppRoleDao extends BaseDao<AppRole, Long>
{

	/**
	 * 根据条件查询应用角色
	 * @param memberTypes
	 * @return
	 */
	public List<AppRole> findList(List<MemberType> memberTypes);
}
