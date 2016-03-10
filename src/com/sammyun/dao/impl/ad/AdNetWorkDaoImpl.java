package com.sammyun.dao.impl.ad;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.ad.AdNetWorkDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.ad.AdNetType;
import com.sammyun.entity.ad.AdNetWork;

/**
 *  DaoImpl - 定向运营商
 * 
 * @author chenyunfeng
 * @version [版本号, Aug 28, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository("adNetWorkDaoImpl")
public class AdNetWorkDaoImpl extends BaseDaoImpl<AdNetWork, Long> implements AdNetWorkDao
{

    @Override
    public List<AdNetWork> finListByNetWork(String netWork)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AdNetWork> criteriaQuery = criteriaBuilder.createQuery(AdNetWork.class);
        Root<AdNetWork> root = criteriaQuery.from(AdNetWork.class);
        criteriaQuery.select(root);

        Predicate restrictions = criteriaBuilder.conjunction();
        if (netWork != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("netWork"), netWork));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

}
