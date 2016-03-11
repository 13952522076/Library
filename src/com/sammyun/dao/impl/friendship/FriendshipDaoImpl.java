package com.sammyun.dao.impl.friendship;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.friendship.FriendshipDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.friendship.Friendship;
import com.sammyun.entity.message.Message;

/**
 * Friendship * DaoImpl - 好友关系
 * 


 */
@Repository("friendshipDaoImpl")
public class FriendshipDaoImpl extends BaseDaoImpl<Friendship, Long> implements FriendshipDao
{

    @Override
    public List<com.sammyun.entity.friendship.Friendship> findFriendship(Boolean hasCreated, Integer count)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Friendship> criteriaQuery = criteriaBuilder.createQuery(Friendship.class);
        Root<Friendship> root = criteriaQuery.from(Friendship.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (hasCreated != null) {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("hasCreated"), hasCreated));
        }
        criteriaQuery.where(restrictions);
        return super.findList(criteriaQuery, null, count, null, null);
    }

}
