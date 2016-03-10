package com.sammyun.service.json;

import java.util.Date;
import java.util.List;

import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.json.JsonFamilyMap;
import com.sammyun.service.BaseService;

/**
 * JsonFamilyMap * Service - 家庭的JSON信息
 * 
 * @author Sencloud Team

 */
public interface JsonFamilyMapService extends BaseService<JsonFamilyMap, Long> 
{
   /**
    * 根据时间和学校去查询
    * <功能详细描述>
    * @param dictSchool
    * @param modifyDate
    * @return
    * @see [类、类#方法、类#成员]
    */
    public List<JsonFamilyMap> findBySchool(Long dictSchoolId,Date modifyDate);
    
    /**
     * 根据角色属性和id去查找
     * <功能详细描述>
     * @param familyId
     * @param memberType
     * @return
     * @see [类、类#方法、类#成员]
     */
    public JsonFamilyMap findByFamilyId (Long familyId, MemberType memberType);
    
    /**
     * 构造数据
     * <功能详细描述>
     * @param memberIds
     * @see [类、类#方法、类#成员]
     */
    public void createData (List<Long> memberIds);
    
}
