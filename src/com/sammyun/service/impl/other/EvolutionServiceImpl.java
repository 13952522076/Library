package com.sammyun.service.impl.other;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.other.EvolutionService;

import com.sammyun.dao.other.EvolutionDao;
import com.sammyun.entity.other.Evolution;

/**
 * Evolution * ServiceImpl - 
 * 


 */
@Service("evolutionServiceImpl")
public class EvolutionServiceImpl extends BaseServiceImpl<Evolution, Long> implements EvolutionService {

    @Resource(name = "evolutionDaoImpl")
    private EvolutionDao evolutionDao;

    @Resource(name = "evolutionDaoImpl")
    public void setBaseDao(EvolutionDao evolutionDao){
        super.setBaseDao(evolutionDao);
    }


}
