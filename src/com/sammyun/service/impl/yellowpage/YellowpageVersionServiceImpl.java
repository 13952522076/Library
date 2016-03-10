package com.sammyun.service.impl.yellowpage;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.yellowpage.YellowpageVersionService;

import com.sammyun.dao.yellowpage.YellowpageVersionDao;
import com.sammyun.entity.yellowpage.YellowpageVersion;

/**
 * ProfilesImage * ServiceImpl - 概况图片数据
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("yellowpageVersionServiceImpl")
public class YellowpageVersionServiceImpl extends BaseServiceImpl<YellowpageVersion, Long> implements YellowpageVersionService {

    @Resource(name = "yellowpageVersionDaoImpl")
    private YellowpageVersionDao yellowpageVersionDao;

    @Resource(name = "yellowpageVersionDaoImpl")
    public void setBaseDao(YellowpageVersionDao yellowpageVersionDao){
        super.setBaseDao(yellowpageVersionDao);
    }

	
}
