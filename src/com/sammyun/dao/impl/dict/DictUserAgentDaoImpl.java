package com.sammyun.dao.impl.dict;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.dict.DictUserAgentDao;
import com.sammyun.entity.dict.DictUserAgent;

/**
 * DictUserAgent * DaoImpl - 用户浏览器数据字典
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("dictUserAgentDaoImpl")
public class DictUserAgentDaoImpl extends BaseDaoImpl<DictUserAgent, Long> implements DictUserAgentDao  {

}
