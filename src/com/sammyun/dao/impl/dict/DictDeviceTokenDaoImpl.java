package com.sammyun.dao.impl.dict;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.dict.DictDeviceTokenDao;
import com.sammyun.entity.dict.DictDeviceToken;

/**
 * DictDeviceToken * DaoImpl - 设备凭证数据字典
 * 


 */
@Repository("dictDeviceTokenDaoImpl")
public class DictDeviceTokenDaoImpl extends BaseDaoImpl<DictDeviceToken, Long> implements DictDeviceTokenDao  {

}
