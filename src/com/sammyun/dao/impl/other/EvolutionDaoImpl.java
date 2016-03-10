package com.sammyun.dao.impl.other;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.other.EvolutionDao;
import com.sammyun.entity.other.Evolution;

/**
 * Evolution * DaoImpl - 
 * 
 * @author Sencloud Team

 */
@Repository("evolutionDaoImpl")
public class EvolutionDaoImpl extends BaseDaoImpl<Evolution, Long> implements EvolutionDao  {

}
