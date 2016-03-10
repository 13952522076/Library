package com.sammyun.dao.impl.ad;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.ad.AdNetTypeDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.ad.AdNetType;

/**
 *  DaoImpl - 定向网络类型
 * 
 * @author chenyunfeng
 * @version [版本号, Aug 28, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository("adNetTypeDaoImpl")
public class AdNetTypeDaoImpl extends BaseDaoImpl<AdNetType, Long> implements AdNetTypeDao
{

    @Override
    public List<AdNetType> finListByNetType(String netType)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AdNetType> criteriaQuery = criteriaBuilder.createQuery(AdNetType.class);
        Root<AdNetType> root = criteriaQuery.from(AdNetType.class);
        criteriaQuery.select(root);

        Predicate restrictions = criteriaBuilder.conjunction();
        if (netType != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("netType"), netType));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

}
