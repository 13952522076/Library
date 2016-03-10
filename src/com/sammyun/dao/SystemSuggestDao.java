package com.sammyun.dao;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.entity.SystemSuggest;

public interface SystemSuggestDao extends BaseDao <SystemSuggest,Long>
{
    /**
     * 根据搜索姓名，其他条件来查询
     * @param realName
     * @param pageable
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Page<SystemSuggest> findByRealName(String realName,Pageable pageable);
}
