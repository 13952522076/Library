package com.sammyun.dao.impl.dict;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.dict.DictDeviceModelDao;
import com.sammyun.entity.dict.DictDeviceModel;

/**
 * DictDeviceModel * DaoImpl - 设备数据字典
 * 
 * @author Sencloud Team

 */
@Repository("dictDeviceModelDaoImpl")
public class DictDeviceModelDaoImpl extends BaseDaoImpl<DictDeviceModel, Long> implements DictDeviceModelDao  {

}
