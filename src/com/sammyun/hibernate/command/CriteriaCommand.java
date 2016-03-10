package com.sammyun.hibernate.command;

import org.hibernate.Criteria;

/**
 * Criteria 条件查询接口 <一句话功能简述> <功能详细描述>
 * 
 * @author xutianlong
 * @version [版本号, Mar 28, 2013]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface CriteriaCommand
{

    public static final String SORT_DESC = "desc";

    public static final String SORT_ASC = "asc";

    public abstract Criteria execute(Criteria criteria);
}
