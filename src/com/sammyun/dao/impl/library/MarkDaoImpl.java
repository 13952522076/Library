package com.sammyun.dao.impl.library;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.library.MarkDao;
import com.sammyun.entity.library.Mark;

/**
 *  DaoImpl - 评分
 */
@Repository("markDaoImpl")
public class MarkDaoImpl extends BaseDaoImpl<Mark, Long> implements MarkDao  {

   

}
