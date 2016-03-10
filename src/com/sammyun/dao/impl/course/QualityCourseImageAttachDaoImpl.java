package com.sammyun.dao.impl.course;

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.course.QualityCourseImageAttachDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.course.QualityCourse;
import com.sammyun.entity.course.QualityCourseImageAttach;

/**
 * Dao - 精品课程 图片附件 列表
 * 
 * @author xutianlong
 * @version [版本号, Jun 11, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository("qualityCourseImageAttachDaoImpl")
public class QualityCourseImageAttachDaoImpl extends BaseDaoImpl<QualityCourseImageAttach, Long> implements
        QualityCourseImageAttachDao
{

    /**
     * 删除 精品课程 图片附件 列表
     * 
     * @param qualityCourse 精品课程
     * @see [类、类#方法、类#成员]
     */
    @Override
    public void deleteByQualityCourse(QualityCourse qualityCourse)
    {
        String jpql = "delete from QualityCourseImageAttach qualityCourseImageAttach where qualityCourseImageAttach.qualityCourse = :qualityCourse";
        entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("qualityCourse", qualityCourse).executeUpdate();
    }
}
