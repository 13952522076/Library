package com.sammyun.dao.poster;

import java.util.Date;
import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.poster.Poster;
import com.sammyun.entity.poster.Poster.PosterPosition;

/**
 * Poster * Dao - 海报
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface PosterDao extends BaseDao<Poster, Long> {

    /**
     * 根据学校查询海报
     */
   public List<Poster> findBySchool(DictSchool dictSchool,PosterPosition posterPosition,Boolean status);

	/**
	 * 根据学校查询海报
	 */
	public List<Poster> findBySchool(DictSchool dictSchool,
			PosterPosition posterPosition, Boolean status, Date updateDate);
}
