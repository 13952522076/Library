package com.sammyun.service.impl.course;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.course.QualityCourseImageAttachDao;
import com.sammyun.entity.course.QualityCourse;
import com.sammyun.entity.course.QualityCourseImageAttach;
import com.sammyun.service.course.QualityCourseImageAttachService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * Service - 精品课程 图片附件 列表
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  xutianlong
 * @version  [版本号, Jun 11, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("qualityCourseImageAttachServiceImpl")
public class QualityCourseImageAttachServiceImpl extends BaseServiceImpl<QualityCourseImageAttach, Long> implements
        QualityCourseImageAttachService
{

    @Resource(name = "qualityCourseImageAttachDaoImpl")
    private QualityCourseImageAttachDao qualityCourseImageAttachDao;

    @Resource(name = "qualityCourseImageAttachDaoImpl")
    public void setBaseDao(QualityCourseImageAttachDao qualityCourseImageAttachDao)
    {
        super.setBaseDao(qualityCourseImageAttachDao);
    }

    /**
     * 删除 精品课程 图片附件 列表
     * 
     * @param qualityCourse 精品课程
     * @see [类、类#方法、类#成员]
     */
    @Override
    public void deleteByQualityCourse(QualityCourse qualityCourse)
    {
        qualityCourseImageAttachDao.deleteByQualityCourse(qualityCourse);
        
    }

}
