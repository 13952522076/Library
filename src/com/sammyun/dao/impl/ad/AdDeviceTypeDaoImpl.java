package com.sammyun.dao.impl.ad;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.ad.AdDeviceTypeDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.ad.AdArea;
import com.sammyun.entity.ad.AdDeviceType;

/**
 *  DaoImpl - 定向设备
 * 
 * @author chenyunfeng
 * @version [版本号, Aug 28, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository("adDeviceTypeDaoImpl")
public class AdDeviceTypeDaoImpl extends BaseDaoImpl<AdDeviceType, Long> implements AdDeviceTypeDao
{

    @Override
    public List<AdDeviceType> finListByDeviceType(String deviceType)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AdDeviceType> criteriaQuery = criteriaBuilder.createQuery(AdDeviceType.class);
        Root<AdDeviceType> root = criteriaQuery.from(AdDeviceType.class);
        criteriaQuery.select(root);

        Predicate restrictions = criteriaBuilder.conjunction();
        if (deviceType != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("deviceType"), deviceType));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

}
