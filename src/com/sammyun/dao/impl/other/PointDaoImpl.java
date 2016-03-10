package com.sammyun.dao.impl.other;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.other.PointDao;
import com.sammyun.entity.other.Point;

/**
 * Point * DaoImpl - 用户积分
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("pointDaoImpl")
public class PointDaoImpl extends BaseDaoImpl<Point, Long> implements PointDao  {

}
