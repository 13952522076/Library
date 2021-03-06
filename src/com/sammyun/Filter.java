/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 筛选
 * 


 */
public class Filter implements Serializable
{

    private static final long serialVersionUID = -8712382358441065075L;

    /**
     * 数据类型
     * 
     * @author xutianlong
     * @version [版本号, Apr 9, 2015]
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    public enum Mold
    {
        /** String */
        s,

        /** Long */
        l,

        /** INTEGER */
        n,

        /** BigDecimal */
        bd,

        /** Float */
        ft,

        /** Short */
        sn,

        /** Date(yyyy-MM-dd) 开始时间 */
        dl,

        /** Date(yyyy-MM-dd) 结束时间 */
        dg,

        /** Dbouble */
        db,
    }

    /**
     * 运算符
     */
    public enum Operator
    {

        /** 等于 */
        eq,

        /** 不等于 */
        ne,

        /** 大于 */
        gt,

        /** 小于 */
        lt,

        /** 大于等于 */
        ge,

        /** 小于等于 */
        le,

        /** 相似 */
        like,

        /** 包含 */
        in,

        /** 为Null */
        isNull,

        /** 不为Null */
        isNotNull;

        /**
         * 从String中获取Operator
         * 
         * @param value 值
         * @return String对应的operator
         */
        public static Operator fromString(String value)
        {
            return Operator.valueOf(value.toLowerCase());
        }
    }

    /** 默认是否忽略大小写 */
    private static final boolean DEFAULT_IGNORE_CASE = false;

    /** 属性 */
    private String property;

    /** 运算符 */
    private Operator operator;

    /** 数据类型 */
    private Mold mold;

    /** 值 */
    private Object value;

    /** 是否忽略大小写 */
    private Boolean ignoreCase = DEFAULT_IGNORE_CASE;

    /**
     * 初始化一个新创建的Filter对象
     */
    public Filter()
    {
    }

    /**
     * 初始化一个新创建的Filter对象
     * 
     * @param property 属性
     * @param operator 运算符
     * @param value 值
     */
    public Filter(String property, Operator operator, Object value)
    {
        this.property = property;
        this.operator = operator;
        this.value = value;
    }

    /**
     * 初始化一个新创建的Filter对象
     * 
     * @param property 属性
     * @param operator 运算符
     * @param value 值
     * @param ignoreCase 忽略大小写
     */
    public Filter(String property, Operator operator, Object value, boolean ignoreCase)
    {
        this.property = property;
        this.operator = operator;
        this.value = value;
        this.ignoreCase = ignoreCase;
    }

    /**
     * 返回等于筛选
     * 
     * @param property 属性
     * @param value 值
     * @return 等于筛选
     */
    public static Filter eq(String property, Object value)
    {
        return new Filter(property, Operator.eq, value);
    }

    /**
     * 返回等于筛选
     * 
     * @param property 属性
     * @param value 值
     * @param ignoreCase 忽略大小写
     * @return 等于筛选
     */
    public static Filter eq(String property, Object value, boolean ignoreCase)
    {
        return new Filter(property, Operator.eq, value, ignoreCase);
    }

    /**
     * 返回不等于筛选
     * 
     * @param property 属性
     * @param value 值
     * @return 不等于筛选
     */
    public static Filter ne(String property, Object value)
    {
        return new Filter(property, Operator.ne, value);
    }

    /**
     * 返回不等于筛选
     * 
     * @param property 属性
     * @param value 值
     * @param ignoreCase 忽略大小写
     * @return 不等于筛选
     */
    public static Filter ne(String property, Object value, boolean ignoreCase)
    {
        return new Filter(property, Operator.ne, value, ignoreCase);
    }

    /**
     * 返回大于筛选
     * 
     * @param property 属性
     * @param value 值
     * @return 大于筛选
     */
    public static Filter gt(String property, Object value)
    {
        return new Filter(property, Operator.gt, value);
    }

    /**
     * 返回小于筛选
     * 
     * @param property 属性
     * @param value 值
     * @return 小于筛选
     */
    public static Filter lt(String property, Object value)
    {
        return new Filter(property, Operator.lt, value);
    }

    /**
     * 返回大于等于筛选
     * 
     * @param property 属性
     * @param value 值
     * @return 大于等于筛选
     */
    public static Filter ge(String property, Object value)
    {
        return new Filter(property, Operator.ge, value);
    }

    /**
     * 返回小于等于筛选
     * 
     * @param property 属性
     * @param value 值
     * @return 小于等于筛选
     */
    public static Filter le(String property, Object value)
    {
        return new Filter(property, Operator.le, value);
    }

    /**
     * 返回相似筛选
     * 
     * @param property 属性
     * @param value 值
     * @return 相似筛选
     */
    public static Filter like(String property, Object value)
    {
        return new Filter(property, Operator.like, value);
    }

    /**
     * 返回包含筛选
     * 
     * @param property 属性
     * @param value 值
     * @return 包含筛选
     */
    public static Filter in(String property, Object value)
    {
        return new Filter(property, Operator.in, value);
    }

    /**
     * 返回为Null筛选
     * 
     * @param property 属性
     * @return 为Null筛选
     */
    public static Filter isNull(String property)
    {
        return new Filter(property, Operator.isNull, null);
    }

    /**
     * 返回不为Null筛选
     * 
     * @param property 属性
     * @return 不为Null筛选
     */
    public static Filter isNotNull(String property)
    {
        return new Filter(property, Operator.isNotNull, null);
    }

    /**
     * 返回忽略大小写筛选
     * 
     * @return 忽略大小写筛选
     */
    public Filter ignoreCase()
    {
        this.ignoreCase = true;
        return this;
    }

    /**
     * 获取属性
     * 
     * @return 属性
     */
    @JsonProperty
    public String getProperty()
    {
        return property;
    }

    /**
     * 设置属性
     * 
     * @param property 属性
     */
    public void setProperty(String property)
    {
        this.property = property;
    }

    /**
     * 获取运算符
     * 
     * @return 运算符
     */
    @JsonProperty
    public Operator getOperator()
    {
        return operator;
    }

    /**
     * 设置运算符
     * 
     * @param operator 运算符
     */
    public void setOperator(Operator operator)
    {
        this.operator = operator;
    }

    /**
     * @return 返回 type
     */
    public Mold getMold()
    {
        return mold;
    }

    /**
     * @param 对type进行赋值
     */
    public void setMold(Mold mold)
    {
        this.mold = mold;
    }

    /**
     * 获取值
     * 
     * @return 值
     */
    @JsonProperty
    public Object getValue()
    {
        return value;
    }

    /**
     * 设置值
     * 
     * @param value 值
     */
    public void setValue(Object value)
    {
        this.value = value;
    }

    /**
     * 获取是否忽略大小写
     * 
     * @return 是否忽略大小写
     */
    @JsonProperty
    public Boolean getIgnoreCase()
    {
        return ignoreCase;
    }

    /**
     * 设置是否忽略大小写
     * 
     * @param ignoreCase 是否忽略大小写
     */
    public void setIgnoreCase(Boolean ignoreCase)
    {
        this.ignoreCase = ignoreCase;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        }
        Filter other = (Filter) obj;
        return new EqualsBuilder().append(getProperty(), other.getProperty()).append(getOperator(), other.getOperator()).append(
                getValue(), other.getValue()).append(getMold(), other.getMold()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37).append(getProperty()).append(getOperator()).append(getValue()).append(
                getMold()).toHashCode();
    }

}
