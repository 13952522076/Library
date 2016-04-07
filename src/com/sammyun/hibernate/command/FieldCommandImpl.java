package com.sammyun.hibernate.command;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class FieldCommandImpl implements CriteriaCommand
{

    private String property;

    private Object value;

    private String operation;

    private QueryFilter filter;

    public FieldCommandImpl(String property, Object value, String operation, QueryFilter filter)
    {
        this.property = property;
        this.value = value;
        this.operation = operation;
        this.filter = filter;
    }

    public String getProperty()
    {
        return property;
    }

    public void setProperty(String property)
    {
        this.property = property;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    public String getOperation()
    {
        return operation;
    }

    public void setOperation(String operation)
    {
        this.operation = operation;
    }

    public Criteria execute(Criteria criteria)
    {
        String propertys[] = property.split("[.]");
        if (propertys != null && propertys.length > 1 && !"vo".equals(propertys[0]))
        {
            for (int i = 0; i < propertys.length - 1; i++)
                if (!filter.getAliasSet().contains(propertys[i]))
                {
                    criteria.createAlias(propertys[i], propertys[i]);
                    filter.getAliasSet().add(propertys[i]);
                }

        }
        if ("LT".equals(operation))
        {
            criteria.add(Restrictions.lt(property, value));
        }

        else if ("GT".equals(operation))
        {
            criteria.add(Restrictions.gt(property, value));
        }

        else if ("LE".equals(operation))
        {
            criteria.add(Restrictions.le(property, value));
        }
        else if ("GE".equals(operation))
        {
            criteria.add(Restrictions.ge(property, value));
        }
        else if ("LK".equals(operation))
        {
            criteria.add(Restrictions.like(property, (new StringBuilder("%")).append(value).append("%").toString()).ignoreCase());
        }
        else if ("LFK".equals(operation))
        {
            criteria.add(Restrictions.like(property, (new StringBuilder()).append(value).append("%").toString()).ignoreCase());
        }
        else if ("RHK".equals(operation))
        {
            criteria.add(Restrictions.like(property, (new StringBuilder("%")).append(value).toString()).ignoreCase());
        }
        else if ("NULL".equals(operation))
        {
            criteria.add(Restrictions.isNull(property));
        }
        else if ("NOTNULL".equals(operation))
        {
            criteria.add(Restrictions.isNotNull(property));
        }
        else if ("EMP".equals(operation))
        {
            criteria.add(Restrictions.isEmpty(property));
        }
        else if ("NOTEMP".equals(operation))
        {
            criteria.add(Restrictions.isNotEmpty(property));
        }
        else if ("IN".equals(operation))
        {
            String[] valueArray = null;

            if (value != null)
            {
                valueArray = value.toString().split(",");
                Long[] longArray = new Long[valueArray.length];
                for (int i = 0; i < valueArray.length; i++)
                {
                    String string = valueArray[i];
                    longArray[i] = Long.valueOf(string);
                }
                criteria.add(Restrictions.in(property, longArray));
            }
        }
        else if ("NEQ".equals(operation))
        {
            criteria.add(Restrictions.ne(property, value));
        }
        else
        {
            criteria.add(Restrictions.eq(property, value));
        }
        return criteria;
    }

    @SuppressWarnings("unchecked")
    public String getPartHql()
    {
        String propertys[] = property.split("[.]");
        if (propertys != null && propertys.length > 1 && !"vo".equals(propertys[0])
                && !filter.getAliasSet().contains(propertys[0]))
            filter.getAliasSet().add(propertys[0]);
        String partHql = "";
        if ("LT".equals(operation))
        {
            partHql = (new StringBuilder(String.valueOf(property))).append(" < ? ").toString();
            filter.getParamValueList().add(value);
        }
        else if ("GT".equals(operation))
        {
            partHql = (new StringBuilder(String.valueOf(property))).append(" > ? ").toString();
            filter.getParamValueList().add(value);
        }
        else if ("LE".equals(operation))
        {
            partHql = (new StringBuilder(String.valueOf(property))).append(" <= ? ").toString();
            filter.getParamValueList().add(value);
        }
        else if ("GE".equals(operation))
        {
            partHql = (new StringBuilder(String.valueOf(property))).append(" >= ? ").toString();
            filter.getParamValueList().add(value);
        }
        else if ("LK".equals(operation))
        {
            partHql = (new StringBuilder(String.valueOf(property))).append(" like ? ").toString();
            filter.getParamValueList().add((new StringBuilder("%")).append(value.toString()).append("%").toString());
        }
        else if ("LFK".equals(operation))
        {
            partHql = (new StringBuilder(String.valueOf(property))).append(" like ? ").toString();
            filter.getParamValueList().add((new StringBuilder(String.valueOf(value.toString()))).append("%").toString());
        }
        else if ("RHK".equals(operation))
        {
            partHql = (new StringBuilder(String.valueOf(property))).append(" like ? ").toString();
            filter.getParamValueList().add((new StringBuilder("%")).append(value.toString()).toString());
        }
        else if ("OR".equals(operation))
        {
            partHql = (new StringBuilder(" or ")).append(String.valueOf(property)).append("=?").toString();
            filter.getParamValueList().add(value);
        }
        else if ("NULL".equals(operation))
        {
            partHql = (new StringBuilder(String.valueOf(property))).append(" is null ").toString();
        }
        else if ("NOTNULL".equals(operation))
        {
            partHql = (new StringBuilder(String.valueOf(property))).append(" is not null ").toString();
        }
        else if (!"EMP".equals(operation) && !"NOTEMP".equals(operation))
        {
            if ("NEQ".equals(operation))
            {
                partHql = (new StringBuilder(String.valueOf(property))).append(" !=? ").toString();
                filter.getParamValueList().add(value);
            }
            else
            {
                partHql = (new StringBuilder(String.valueOf(partHql))).append(property).append(" =? ").toString();
                filter.getParamValueList().add(value);
            }
        }
        return partHql;
    }
}
