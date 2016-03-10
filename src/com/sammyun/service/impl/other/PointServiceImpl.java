package com.sammyun.service.impl.other;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.other.PointService;

import com.sammyun.dao.other.PointDao;
import com.sammyun.entity.other.Point;

/**
 * Point * ServiceImpl - 用户积分
 * 
 * @author Sencloud Team

 */
@Service("pointServiceImpl")
public class PointServiceImpl extends BaseServiceImpl<Point, Long> implements PointService {

    @Resource(name = "pointDaoImpl")
    private PointDao pointDao;

    @Resource(name = "pointDaoImpl")
    public void setBaseDao(PointDao pointDao){
        super.setBaseDao(pointDao);
    }


}
