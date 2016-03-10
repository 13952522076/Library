package com.sammyun.service.impl.dict;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.dict.DictGradeDao;
import com.sammyun.entity.dict.DictGrade;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.dict.DictGradeService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * DictSchool * ServiceImpl - 学校
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("dictGradeServiceImpl")
public class DictGradeServiceImpl extends BaseServiceImpl<DictGrade, Long> implements DictGradeService 
{

    @Resource(name = "dictGradeDaoImpl")
    private DictGradeDao dictGradeDao;

    @Resource(name = "dictGradeDaoImpl")
    public void setBaseDao(DictGradeDao dictGradeDao){
        super.setBaseDao(dictGradeDao);
    }

    @Override
    public List<DictGrade> findByName(String name, DictSchool dictSchool)
    {
        // TODO Auto-generated method stub
        return dictGradeDao.findByName(name, dictSchool);
    }
}
