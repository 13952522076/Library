package com.sammyun.dao.impl.attendance;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.attendance.WorkSettingDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.attendance.WorkSetting;
import com.sammyun.entity.dict.DictSchool;

/**
 * DaoImpl - 班次设置
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("workSettingDaoImpl")
public class WorkSettingDaoImpl extends BaseDaoImpl<WorkSetting, Long> implements WorkSettingDao
{

    @Override
    public List<WorkSetting> findBySchool(DictSchool dictSchool)
    {
        List<WorkSetting> workSettings = new ArrayList<WorkSetting>();
        if(dictSchool==null){
            return workSettings;
        }
        String jpql = "select workSetting from WorkSetting workSetting where workSetting.dictSchool = :dictSchool";
        return entityManager.createQuery(jpql, WorkSetting.class)
                .setFlushMode(FlushModeType.COMMIT)
                .setParameter("dictSchool", dictSchool)
                .getResultList();
    }

    @Override
    public List<WorkSetting> findBySchool(DictSchool dictSchool, Boolean isDefalut)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<WorkSetting> criteriaQuery = criteriaBuilder.createQuery(WorkSetting.class);
        Root<WorkSetting> root = criteriaQuery.from(WorkSetting.class);
        criteriaQuery.select(root);

        Predicate restrictions = criteriaBuilder.conjunction();
        if (dictSchool != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("dictSchool"), dictSchool));
        }
        if (isDefalut != null)
        {
                restrictions = criteriaBuilder.and(restrictions,
                        criteriaBuilder.equal(root.get("isDefalut"), isDefalut));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    } 

}
