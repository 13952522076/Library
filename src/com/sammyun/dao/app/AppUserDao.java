package com.sammyun.dao.app;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.BaseDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.app.App;
import com.sammyun.entity.app.AppUser;
import com.sammyun.entity.app.App.OperatingSystem;

/**
 * Dao - 用户应用清单
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface AppUserDao extends BaseDao<AppUser, Long>
{

	/**
     * 查询用户应用清单
     * 
     * @param member
     * @param isDelete
     * @param operatingSystem
     * @param pageable
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Page<AppUser> findByMember(Member member,Boolean isDelete,OperatingSystem operatingSystem,Pageable pageable);
    
    /**
     * 	 根据 用户id 和已安装的appid 查询用户应用记录
     * @param member
     * @param app
     * @return
     */
    public AppUser findByParam(Member member, App app);
}
