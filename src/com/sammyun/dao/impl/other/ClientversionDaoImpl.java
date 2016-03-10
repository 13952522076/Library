package com.sammyun.dao.impl.other;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.other.ClientversionDao;
import com.sammyun.entity.other.Clientversion;

/**
 * Clientversion * DaoImpl - 客户端版本管理
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("clientversionDaoImpl")
public class ClientversionDaoImpl extends BaseDaoImpl<Clientversion, Long> implements ClientversionDao  {

}
