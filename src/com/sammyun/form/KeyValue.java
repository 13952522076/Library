package com.sammyun.form;

/**
 * 定义key－value的类，代替map，以对象形式,方便操作 <功能详细描述> 实现对list的按照value排序接口，value值强转Float
 * 
 * @author maxu
 * @version [版本号, 2016年4月5日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class KeyValue implements Comparable<KeyValue>
{
    /** 定义key元素 */
    private Object key;

    /** 定义value值 */
    private Object value;

    /** 定义key，value构造函数 */
    public KeyValue(Object key, Object value)
    {
        this.key = key;
        this.value = value;
    }

    public Object getKey()
    {
        return key;
    }

    public void setKey(Object key)
    {
        this.key = key;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    @Override
    public int compareTo(KeyValue o)
    {
        //从大到小排序
        try
        {
            
            Float one = Float.parseFloat(this.getValue().toString());
            Float two = Float.parseFloat(o.getValue().toString());
            return two.compareTo(one);
        }
        catch (Exception e)
        {
           return o.compareTo(this);
        }

        

    }
}
