/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.MemberDeviceInfoDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.MemberDeviceInfo;
import com.sammyun.entity.dict.ClassTeacherMap;

/**
 * Dao - 会员设备信息列表
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("memberDeviceInfoDaoImpl")
public class MemberDeviceInfoDaoImpl extends BaseDaoImpl<MemberDeviceInfo, Long> implements MemberDeviceInfoDao {

	/**
	 * 查询设备信息
	 * 
	 * @param deviceToken
	 *            设备token
	 * @param uuid
	 *             设备uuid      
	 * @return 设备信息列表
	 */
	@Override
	public List<MemberDeviceInfo> findDevice(String deviceToken, String uuid) {
		
		List<MemberDeviceInfo> memberDeviceInfoList = new ArrayList<MemberDeviceInfo>();
		if (deviceToken == null || uuid ==null) {
			return memberDeviceInfoList;
		}
		String jpql = "select memberDeviceInfo from MemberDeviceInfo memberDeviceInfo where lower(memberDeviceInfo.deviceToken) = lower(:deviceToken) and lower(memberDeviceInfo.uuid) = lower(:uuid)";
		memberDeviceInfoList= (List<MemberDeviceInfo>)entityManager.
				createQuery(jpql, MemberDeviceInfo.class)
				.setFlushMode(FlushModeType.COMMIT)
				.setParameter("deviceToken", deviceToken)
				.setParameter("uuid", uuid)
				.getResultList();
	   return memberDeviceInfoList;
	}

    @Override
    public List<MemberDeviceInfo> findDevice(Member member)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MemberDeviceInfo> criteriaQuery = criteriaBuilder.createQuery(MemberDeviceInfo.class);
        Root<MemberDeviceInfo> root = criteriaQuery.from(MemberDeviceInfo.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();

        if (member != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("member"), member));
        }

        restrictions = criteriaBuilder.and(restrictions, root.get("deviceToken").isNotNull());
        
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

}
