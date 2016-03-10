package com.sammyun.dao.impl.dict;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.dict.DictClientVerDao;
import com.sammyun.entity.dict.DictClientVer;

/**
 * DictClientVer * DaoImpl - 客户端版本号数据字典
 * 
 * @author Sencloud Team

 */
@Repository("dictClientVerDaoImpl")
public class DictClientVerDaoImpl extends BaseDaoImpl<DictClientVer, Long> implements DictClientVerDao  {

}
