package com.sammyun.dao.impl.ad;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.ad.AdAreaDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.ad.AdArea;

/**
 *  DaoImpl - 投放 区域（定向地区）
 * 
 * @author chenyunfeng
 * @version [版本号, Aug 28, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository("adAreaDaoImpl")
public class AdAreaDaoImpl extends BaseDaoImpl<AdArea, Long> implements AdAreaDao
{

    @Override
    public List<AdArea> finListByFullName(String fullName)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AdArea> criteriaQuery = criteriaBuilder.createQuery(AdArea.class);
        Root<AdArea> root = criteriaQuery.from(AdArea.class);
        criteriaQuery.select(root);

        Predicate restrictions = criteriaBuilder.conjunction();
        if (fullName != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("fullName"), fullName));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

}
