package com.sammyun.service.poster;

import java.util.Date;
import java.util.List;

import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.poster.Poster;
import com.sammyun.entity.poster.Poster.PosterPosition;
import com.sammyun.service.BaseService;

/**
 * Poster * Service - 海报
 * 
 * @author Sencloud Team

 */
public interface PosterService extends BaseService<Poster, Long> {

    /**
     * 根据学校查询海报
     */
   public List<Poster> findBySchool(DictSchool dictSchool,PosterPosition posterPosition,Boolean status);
   

   public List<Poster> findBySchool(DictSchool dictSchool,PosterPosition posterPosition,Boolean status, Date updateDate);
}
