package com.sammyun.hibernate.command;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

/**
 * 查询接口
 * 
 * @author xutianlong
 * @version [版本号, Apr 9, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ExampleCommandImpl implements CriteriaCommand
{

    private Object pojoExample;

    public void setPojoExample(Object pojoEx)
    {
        pojoExample = pojoEx;
    }

    public ExampleCommandImpl(Object pojoExample)
    {
        this.pojoExample = null;
        this.pojoExample = pojoExample;
    }

    public Criteria execute(Criteria criteria)
    {
        if (pojoExample != null)
        {
            Example exampleRestriction = Example.create(pojoExample);
            criteria.add(exampleRestriction);
        }
        return criteria;
    }
}
