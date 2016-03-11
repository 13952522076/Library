/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sammyun.Filter;
import com.sammyun.Order;
import com.sammyun.dao.TagDao;
import com.sammyun.entity.Tag;
import com.sammyun.entity.Tag.Type;
import com.sammyun.service.TagService;


/**
 * Service - 标签
 * 


 */
@Service("tagServiceImpl")
public class TagServiceImpl extends BaseServiceImpl<Tag, Long> implements TagService {

	@Resource(name = "tagDaoImpl")
	private TagDao tagDao;

	@Resource(name = "tagDaoImpl")
	public void setBaseDao(TagDao tagDao) {
		super.setBaseDao(tagDao);
	}

	@Transactional(readOnly = true)
	public List<Tag> findList(Type type) {
		return tagDao.findList(type);
	}

	@Transactional(readOnly = true)
	@Cacheable("tag")
	public List<Tag> findList(Integer count, List<Filter> filters, List<Order> orders, String cacheRegion) {
		return tagDao.findList(null, count, filters, orders);
	}

	@Override
	@Transactional
	@CacheEvict(value = "tag", allEntries = true)
	public void save(Tag tag) {
		super.save(tag);
	}

	@Override
	@Transactional
	@CacheEvict(value = "tag", allEntries = true)
	public Tag update(Tag tag) {
		return super.update(tag);
	}

	@Override
	@Transactional
	@CacheEvict(value = "tag", allEntries = true)
	public Tag update(Tag tag, String... ignoreProperties) {
		return super.update(tag, ignoreProperties);
	}

	@Override
	@Transactional
	@CacheEvict(value = "tag", allEntries = true)
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	@Transactional
	@CacheEvict(value = "tag", allEntries = true)
	public void delete(Long... ids) {
		super.delete(ids);
	}

	@Override
	@Transactional
	@CacheEvict(value = "tag", allEntries = true)
	public void delete(Tag tag) {
		super.delete(tag);
	}
	
	@Transactional(readOnly = true)
	public Long getCurrentTagId() {
		RequestAttributes requestAttributes = RequestContextHolder
				.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes)
					.getRequest();
			Long  tagId = (Long) request.getSession()
					.getAttribute(Tag.TAGID_ATTRIBUTE_NAME);
			if (tagId != null) {
				return tagId;
			}
		}
		return null;
	
	}


}