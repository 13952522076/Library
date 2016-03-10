package com.sammyun.service.impl.gd;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.gd.DiaryCollectionDao;
import com.sammyun.entity.gd.DiaryCollection;
import com.sammyun.service.gd.DiaryCollectionService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * DiaryCollection * ServiceImpl - 
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("diaryCollectionServiceImpl")
public class DiaryCollectionServiceImpl extends BaseServiceImpl<DiaryCollection, Long> implements DiaryCollectionService
{
    
    @Resource(name = "diaryCollectionDaoImpl")
    private DiaryCollectionDao diaryCollectionDao;

    @Resource(name = "diaryCollectionDaoImpl")
    public void setBaseDao(DiaryCollectionDao diaryCollectionDao)
    {
        super.setBaseDao(diaryCollectionDao);
    }

}
