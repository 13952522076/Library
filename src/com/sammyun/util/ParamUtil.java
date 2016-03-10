package com.sammyun.util;

import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 参数工具类
 * 
 * @author  xutianlong
 * @version  [版本号, Apr 9, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ParamUtil
{
    private static Log logger = LogFactory.getLog(ParamUtil.class);

    /**
     * 根据前台参数名传递过来的值 类似Q_sex_N_EQ ，以_截取，第一个代表是查询Q=Query ,sex代表字段、属性，N代表字段
     * 、属性的类型，EQ代表是使用什么方式来查询(包括模糊查询，Equals查询等)，EQ是Equals的简写
     * 
     * convertObject方法用于转换类型到实际的数据类型
     * 
     * @param type
     * @param paramValue
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Object convertObject(String type, String paramValue)
    {
        if (StringUtils.isEmpty(paramValue))
            return null;
        Object value = null;
        try
        {
            if ("S".equals(type))
            {
                value = paramValue;
            } 
            else if ("L".equals(type))
            {
                value = new Long(paramValue);
            }
            else if ("N".equals(type))
            {
                value = new Integer(paramValue);
            }
            else if ("BD".equals(type))
            {
                value = new BigDecimal(paramValue);
            }
            else if ("FT".equals(type))
            {
                value = new Float(paramValue);
            }
            else if ("SN".equals(type))
            {
                value = new Short(paramValue);
            }
            else if ("D".equals(type))
            {
                value = DateUtils.parseDate(paramValue, new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"});
            }
            else if ("DL".equals(type))
            {
                Calendar cal = Calendar.getInstance();
                cal.setTime(DateUtils.parseDate(paramValue, new String[] {"yyyy-MM-dd"}));
                value = DateUtil.setStartDay(cal).getTime();
            }
            else if ("DG".equals(type))
            {
                Calendar cal = Calendar.getInstance();
                cal.setTime(DateUtils.parseDate(paramValue, new String[] {"yyyy-MM-dd"}));
                value = DateUtil.setEndDay(cal).getTime();
            }
            else if ("DB".equals(type))
            {
                value= new Double(paramValue);
            }
            else
            {
                value = paramValue;
            }
        }
        catch (Exception ex)
        {
            logger.error("the data value is not right for the query filed type:" + ex.getMessage());
        }
        return value;
    }
    
}

