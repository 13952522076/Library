package com.sammyun.service.course;

import com.sammyun.entity.course.QualityCourse;
import com.sammyun.entity.course.QualityCourseImageAttach;
import com.sammyun.service.BaseService;

/**
 * Service -  精品课程 图片附件 列表
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  xutianlong
 * @version  [版本号, Jun 11, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface QualityCourseImageAttachService extends BaseService<QualityCourseImageAttach, Long>
{

    /**
     * 删除 精品课程 图片附件 列表
     * 
     * @param qualityCourse 精品课程
     * @see [类、类#方法、类#成员]
     */
    void deleteByQualityCourse(QualityCourse qualityCourse);
}
