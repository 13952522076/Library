package com.sammyun.dao.impl.yellowpage;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.yellowpage.YellowpageDao;
import com.sammyun.entity.yellowpage.Yellowpage;

/**
 * Yellowpage * DaoImpl - 黄页数据
 * 
 * @author Sencloud Team

 */
@Repository("yellowpageDaoImpl")
public class YellowpageDaoImpl extends BaseDaoImpl<Yellowpage, Long> implements YellowpageDao  {

}
