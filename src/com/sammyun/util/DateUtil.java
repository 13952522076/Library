/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utils - 时间工具类
 * 
 * @author tianlongxu
 */
public class DateUtil
{

    private static final Log logger = LogFactory.getLog(DateUtil.class);

    private static String year_format = "yyyy";

    @SuppressWarnings("unused")
    private static String month_format = "yyyyMM";

    private static String default_format = "yyyy-MM-dd";

    private static String time_format = "yyyyMMddHHmmss";

    private final static String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 返回下一年_格式yyyy
     * 
     * @return int
     */
    public static int getNextYear()
    {
        return new GregorianCalendar().get(Calendar.YEAR) + 1;
    }

    /**
     * 返回当前的年
     * 
     * @return int
     */
    public static int getCurrYear()
    {
        return new GregorianCalendar().get(Calendar.YEAR);
    }

    /**
     * 获取某年第一天日期_格式yyyy-MM-dd
     * 
     * @param year 年份
     * @return String
     */
    public static String getCurrYearFirstDay(int year)
    {
        SimpleDateFormat format = new SimpleDateFormat(default_format);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return format.format(currYearFirst);
    }

    /**
     * 根据一个日期，返回是星期几的数字_星期天:1,星期一:2....星期六:7
     * 
     * @param dateString String
     * @return int
     */
    public static int getWeek(String dateString)
    {
        Date date = string2Date(dateString);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 根据一个日期，返回这个日期所在月份的第一天到这个日期的总共的周六周日的天数
     * 
     * @param dateString String
     * @return int
     * @throws ParseException
     */
    public static int getWeekDays(Date date) throws ParseException
    {
        Calendar calendar = DateUtils.toCalendar(date);
        int year = getYear(date);
        int month = getMonth(date);
        int maxDay = calendar.get(Calendar.DAY_OF_MONTH);
        int days = 0;
        // 判断该日期是否是当前年月
        if (isEqualYM(date))
        {
            for (int day = 1; day < maxDay; day++)
            {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date datestr = simpleDateFormat.parse(year + "-" + month + "-" + day);
                Calendar c = Calendar.getInstance();
                c.setTime(datestr);
                if (c.get(Calendar.DAY_OF_WEEK) == 1 || c.get(Calendar.DAY_OF_WEEK) == 7)
                {
                    days++;
                }
            }
        }
        else
        {
            for (int day = 1; day <= maxDay; day++)
            {

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date datestr = simpleDateFormat.parse(year + "-" + month + "-" + day);
                Calendar c = Calendar.getInstance();
                c.setTime(datestr);
                if (c.get(Calendar.DAY_OF_WEEK) == 1 || c.get(Calendar.DAY_OF_WEEK) == 7)
                {
                    days++;
                }
            }
        }
        return days;
    }

    public static String getDateByAddDays(String inDate, int days, int _iType)
    {
        Date dateStr = string2Date(inDate);
        Date tempDate = getDateByAddDays(dateStr, days);
        return date2String(tempDate, _iType);
    }

    public static String date2String(Date date, int _iType)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(time_format);
        String strTemp = sdf.format(date);
        SimpleDateFormat formatter = null;
        switch (_iType)
        {
            case 0: // yyyymmdd
                strTemp = strTemp.substring(0, 8);
                break;
            case 2: // yyyy-mm
                formatter = new SimpleDateFormat("yyyy-MM");
                strTemp = formatter.format(date);
                break;
            case 4:// yyyy
                strTemp = strTemp.substring(0, 4);
                break;
            case 6: // yymmdd
                strTemp = strTemp.substring(2, 8);
                break;
            case 8: // yyyymmdd
                strTemp = strTemp.substring(0, 8);
                break;
            case 10: // yyyy-mm-dd
                formatter = new SimpleDateFormat("yyyy-MM-dd");
                strTemp = formatter.format(date);
                break;
            case -6: // HHmmss
                strTemp = strTemp.substring(8);
                break;
            case -8: // HH:mm:ss
                formatter = new SimpleDateFormat("HH:mm:ss");
                strTemp = formatter.format(date);
                break;
            default:
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                strTemp = formatter.format(date);
                break;
        }
        return strTemp;
    }

    /**
     * 获得指定日期前后的日期，返回日期型值
     * 
     * @param inDate 指定的日期
     * @param days 加减天数
     * @return Date
     */
    public static Date getDateByAddDays(Date inDate, int days)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(inDate);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * 获得指定日期前后的日期，返回日期型值
     * 
     * @param inDate 指定的日期
     * @param days 加减天数
     * @return Date
     */
    public static Date getDateByAddMonth(Date inDate, int month)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(inDate);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 得到当前时间,格式为yyyyMMddhhmmss
     * 
     * @return String
     */
    public static String generateTime()
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }

    /**
     * 得到当前时间,格式为yyyyMMddhhmmssSSS
     * 
     * @return String
     */
    public static String generateTimeHM()
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return format.format(new Date());
    }

    /**
     * 根据格式生成当前日期时间
     * 
     * @param formatStr
     * @return
     */
    public static String generateDateTime(String formatStr)
    {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(new Date());
    }

    /**
     * 根据yyyy-MM-dd得到月份
     * 
     * @param dateString String
     * @return int
     */
    public static int getMonthFromYear(String dateString)
    {
        Date date = string2Date(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 得到指定年的所有天数
     * 
     * @param year String
     * @return day int
     */
    public static int getDayFromYear(String year)
    {
        SimpleDateFormat format = new SimpleDateFormat(year_format);
        Date date;
        int day = 0;
        try
        {
            date = format.parse(year);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            day = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * 将日期字符串转换成日期型，日期格式为"yyyy-MM-dd"
     * 
     * @param dateString 指定的日期字符串，格式为yyyyMMdd 或者yyyy-MM-dd
     * @return Date
     */
    public final static Date string2Date(String dateString)
    {
        if (dateString == null || dateString.trim().length() == 0)
        {
            return new Date(0);
        }
        try
        {
            String strFormat = "";
            switch (dateString.length())
            {
                case 4:
                    strFormat = "yyyy";
                    break;
                case 6: // yymmdd
                    strFormat = "yyMMdd";
                    break;
                case 8: // yyyymmdd
                    strFormat = "yyyyMMdd";
                    break;
                case 10: // yyyy-mm-dd
                    strFormat = "yyyy-MM-dd";
                    break;
                case 14:
                    strFormat = "yyyyMMddHHmmss";
                    break;
                default:
                    strFormat = "yyyy-MM-dd HH:mm:ss";
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormat);
            // 这里没有做非法日期的判断，比如：＂2007-05-33＂
            java.util.Date timeDate = simpleDateFormat.parse(dateString);
            return timeDate;
        }
        catch (Exception e)
        {
            return new Date(0);
        }
    }

    /**
     * 获取时间
     * 
     * @return
     */
    public static String getCurrentDate()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        return df.format(new Date());
    }

    /**
     * 获取当前时间按时间
     * 
     * @return
     */
    public static Date getDate()
    {
        return new Date();
    }

    /**
     * 得到两个时间的差值，单位是小时
     * 
     * @param beginDate
     * @param endDate
     * @return
     */
    public static double getHourBetweenDates(Date beginDate, Date endDate)
    {

        long l1 = endDate.getTime();
        long l2 = beginDate.getTime();

        double cc = l1 - l2;
        return cc / (60 * 60 * 1000);
    }

    /**
     * 得到两个时间的差值，单位是分钟
     * 
     * @param beginDate
     * @param endDate
     * @return
     */
    public static double getMinuteBetweenDates(Date beginDate, Date endDate)
    {

        double cc = endDate.getTime() - beginDate.getTime();
        return cc / (60 * 1000);
    }

    /**
     * 得到两个时间的差值，单位天
     * 
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int getDateBetweenDates(Date beginDate, Date endDate)
    {
        String bd = date2String(beginDate, 10);
        String ed = date2String(endDate, 10);
        Date bdd = string2Date(bd);
        Date edd = string2Date(ed);
        double cc = edd.getTime() - bdd.getTime();
        return ((int) cc / (24 * 60 * 60 * 1000));
    }

    public static void main(String[] args)
    {
        Date hastime2 = string2Date("2007-05-33");
        System.out.println(hastime2);
    }

    public static String obtainMonth(String dateStr, int m)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String str = "";
        try
        {
            Date d1 = df.parse(dateStr);
            Calendar g = Calendar.getInstance();
            g.setTime(d1);
            g.add(Calendar.MONTH, m);
            Date d2 = g.getTime();
            str = df.format(d2);
            str = str.replace("-", "");
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return str;
    }

    // 获得20130906 这样格式的
    public static String obtainCurrteDate()
    {
        Calendar cal = Calendar.getInstance();
        int m = cal.get(Calendar.MONTH);
        int d = cal.get(Calendar.DATE);
        String ms = "";
        if (m + 1 < 10)
        {
            ms = "0" + (m + 1);

        }
        else
        {
            ms = String.valueOf(m + 1);
        }

        String ds = "";

        if (d < 10)
        {
            ds = "0" + d;

        }
        else
        {
            ds = String.valueOf(d);
        }

        return String.valueOf(cal.get(Calendar.YEAR)) + ms + ds;
    }

    /**
     * 获取一个人的年龄
     * 
     * @param birthDay 生日
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public static Integer getAge(Date birthDay)
    {
        if (birthDay == null)
        {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay))
        {
            return 0;
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;// 注意此处，如果不加1的话计算结果是错误的
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth)
        {
            if (monthNow == monthBirth)
            {
                if (dayOfMonthNow < dayOfMonthBirth)
                {
                    age--;
                }
            }
            else if (monthNow < monthBirth)
            {
                age--;
            }
        }
        else
        {
            // monthNow<monthBirth
            // donothing
        }

        return age;
    }

    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * 
     * @return 以yyyyMMddHHmmss为格式的当前系统时间
     */
    public static String getOrderNum()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        return sdf.format(date);
    }

    public static Calendar setStartDay(Calendar cal)
    {
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        return cal;
    }

    public static Calendar setEndDay(Calendar cal)
    {
        cal.set(11, 23);
        cal.set(12, 59);
        cal.set(13, 59);
        return cal;
    }

    public static void copyYearMonthDay(Calendar destCal, Calendar sourceCal)
    {
        destCal.set(1, sourceCal.get(1));
        destCal.set(2, sourceCal.get(2));
        destCal.set(5, sourceCal.get(5));
    }

    public static String formatEnDate(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        return sdf.format(date).replaceAll("上午", "AM").replaceAll("下午", "PM");
    }

    /**
     * 日期转换<一句话功能简述> <功能详细描述>
     * 
     * @param dateString
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Date parseDate(String dateString)
    {
        Date date = null;
        try
        {
            date = DateUtils.parseDate(dateString, new String[] {"yyyy-MM-dd HH:mm:ss.SSS", "yyyy-MM-dd HH:mm:ss",
                    "yyyy-MM-dd", "yyyy/MM/dd HH:mm:ss.SSS", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd"});
        }
        catch (Exception ex)
        {
            logger.error((new StringBuilder("Pase the Date(")).append(dateString).append(") occur errors:").append(
                    ex.getMessage()).toString());
            return null;
        }
        return date;
    }

    /**
     * 获取当前系统时间
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Date getFullCurrentDate()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");// 设置日期格式
        String currentDateStr = df.format(new Date()); // new Date()为获取当前系统时间
        return DateUtil.parseDate(currentDateStr);
    }

    /**
     * 年月格式的时间转换为年月日 如果表示月初，那么转换格式是 2012-01-01，否则就是月尾
     * 
     * @param date 输入的年月 格式是 YY-MM
     * @param isEnd 是否是月尾
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String parseEarlyEndMonth(String date, boolean isEnd)
    {
        // 计算月初
        if (!isEnd)
        {
            return date.concat("-01");
        }
        else
        {
            String[] yearMonths = date.split("-");
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, Integer.valueOf(yearMonths[0]));
            cal.set(Calendar.MONTH, Integer.valueOf(yearMonths[1]) - 1);
            // 输入年月获取，这个月的最大天数
            Integer maxDate = cal.getActualMaximum(Calendar.DATE);
            return date.concat("-".concat(maxDate.toString()));
        }
    }

    /**
     * 获取年初时间点 比如2010-01-01
     * 
     * @param startCenterReortYearDate
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String lastInitDate(String startCenterReortYearDate)
    {
        return DateUtil.parseEarlyEndMonth(String.valueOf(Integer.valueOf(startCenterReortYearDate) - 1) + "-" + "01",
                false);
    }

    /**
     * 获取x年x月月尾 比如2010-01-31
     * 
     * @param startCenterReortYearDate 年
     * @return startCenterReortMonthDate 月
     * @see [类、类#方法、类#成员]
     */
    public static String lastEndDate(String startCenterReortYearDate, String startCenterReortMonthDate)
    {
        return DateUtil.parseEarlyEndMonth(String.valueOf(Integer.valueOf(startCenterReortYearDate) - 1) + "-"
                + startCenterReortMonthDate, true);
    }

    /**
     * 计算出离当前日期datas天的日期,若datas小于0表示当前日期之前datas天，若datas大于0表当前日期之后datas天
     * 
     * @param 要计算的天数
     * @return 得到日期
     */
    public static Date getDate(int datas)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(GregorianCalendar.DATE, datas);
        String begin = new java.sql.Date(calendar.getTime().getTime()).toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        try
        {
            beginDate = sdf.parse(begin);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return beginDate;
    }

    /**
     * 计算出离beginDate日期datas天的日期,若datas小于0表示当前日期之前datas天，若datas大于0表当前日期之后datas天
     * 
     * @param 要计算的天数
     * @return 得到日期
     */
    public static Date getDate(Date beginDate, int datas)
    {
        Calendar beginCal = Calendar.getInstance();
        beginCal.setTime(beginDate);
        GregorianCalendar calendar = new GregorianCalendar(beginCal.get(Calendar.YEAR), beginCal.get(Calendar.MONTH),
                beginCal.get(Calendar.DATE));
        calendar.add(GregorianCalendar.DATE, datas);
        String begin = new java.sql.Date(calendar.getTime().getTime()).toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = null;
        try
        {
            endDate = sdf.parse(begin);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return endDate;
    }

    /**
     * 得到本日的前几个月时间 如果number=2当日为2007-9-1,那么获得2007-7-1<一句话功能简述> <功能详细描述>
     * 
     * @param number
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getDateBeforeMonth(int number)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -number);
        return getFormatDate(cal.getTime(), DATE_FORMAT);
    }

    /**
     * 得到beginDate的前几个月时间 如果number=2当日为2007-9-1,那么获得2007-7-1<一句话功能简述> <功能详细描述>
     * 
     * @param beginDate
     * @param number
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getDateBeforeMonth(Date beginDate, int number)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        cal.add(Calendar.MONTH, -number);
        return getFormatDate(cal.getTime(), DATE_FORMAT);
    }

    /**
     * 得到beginDate的前几个月时间 如果number=2当日为2007-9-1,那么获得2007-7-1<一句话功能简述> <功能详细描述>
     * 
     * @param beginDate
     * @param number
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getDateBeforeMonth(String beginDate, int number)
    {
        Date parseBeginDate = parseDate(beginDate);
        return getDateBeforeMonth(parseBeginDate, number);
    }

    /**
     * 根据格式得到格式化后的日期
     * 
     * @param currDate 要格式化的日期
     * @param format 日期格式，如yyyy-MM-dd
     * @see java.text.SimpleDateFormat#format(java.util.Date)
     * @return String 返回格式化后的日期，格式由参数<code>format</code>
     *         定义，如yyyy-MM-dd，如2006-02-15
     */
    public static String getFormatDate(java.util.Date currDate, String format)
    {
        if (currDate == null)
        {
            return "";
        }
        SimpleDateFormat dtFormatdB = null;
        try
        {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.format(currDate);
        }
        catch (Exception e)
        {
            dtFormatdB = new SimpleDateFormat(DATE_FORMAT);
            try
            {
                return dtFormatdB.format(currDate);
            }
            catch (Exception ex)
            {
            }
        }
        return null;
    }

    /**
     * 根据格式得到格式化后的日期
     * 
     * @param currDate 要格式化的日期
     * @param format 日期格式，如yyyy-MM-dd
     * @see java.text.SimpleDateFormat#format(java.util.Date)
     * @return String 返回格式化后的日期，格式由参数<code>format</code>
     *         定义，如yyyy-MM-dd，如2006-02-15
     */
    public static String getFormatDate(String currDate, String format)
    {
        Date dataDate = DateUtil.parseDate(currDate);
        if (dataDate == null)
        {
            return "";
        }
        SimpleDateFormat dtFormatdB = null;
        try
        {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.format(dataDate);
        }
        catch (Exception e)
        {
            dtFormatdB = new SimpleDateFormat(DATE_FORMAT);
            try
            {
                return dtFormatdB.format(dataDate);
            }
            catch (Exception ex)
            {
            }
        }
        return null;
    }

    /**
     * 功能：得到当前年份年初 格式为：xxxx-yy-zz (eg: 2007-01-01)<br>
     * 
     * @return String
     * @author pure
     */
    public static String getEarlyYear()
    {
        Calendar localTime = Calendar.getInstance();
        int x = localTime.get(Calendar.YEAR);
        return x + "-01" + "-01";
    }

    /**
     * 通过时分拼接出时分时间
     * 
     * @param hour
     * @param minute
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String spliceTime(String hour, String minute)
    {
        if (hour == null || minute == null)
        {
            return "";
        }
        if (hour.length() < 2)
        {
            hour = "0" + hour;
        }
        if (minute.length() < 2)
        {
            minute = "0" + minute;
        }
        String time = hour + ":" + minute;
        return time;
    }

    /**
     * 判断给定日期年月是否与当前年月是否相等
     * 
     * @param date
     * @return true/false
     */
    public static Boolean isEqualYM(Date date)
    {
        return DateUtil.date2String(DateUtil.getDate(), 2).equals(DateUtil.date2String(date, 2));
    }

    /**
     * 功能：得到Date的年
     * 
     * @param date
     * @return
     */
    public static int getYear(Date date)
    {
        Calendar calendar = DateUtils.toCalendar(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 功能：得到Date的月份
     * 
     * @param date
     * @return
     */
    public static int getMonth(Date date)
    {
        Calendar calendar = DateUtils.toCalendar(date);
        return calendar.get(Calendar.MONDAY) + 1;
    }

    /**
     * 获取某个日期月份第一天的日期
     * 
     * @param date
     * @return 该月份第一天的日期
     */
    public static Date getFirstDate(Date date) throws ParseException
    {
        Calendar calendar = DateUtils.toCalendar(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        date = simpleDateFormat.parse(year + "-" + month + "-" + 1);
        return date;
    }

    /**
     * 获取指定日期的最后一天
     * 
     * @param date
     * @return 最后一天的格式化日期
     */
    public static Date getDefaultDay(Date date, String format)
    {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar lastDate = Calendar.getInstance();
        lastDate.setTime(date);
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1 号
        lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1 号
        lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
        str = sdf.format(lastDate.getTime());
        return string2Date(str);
    }

    /**
     * 获取在一个日期区间内 属于指定月份日期的天数 （精确到日）
     * 
     * @param date
     * @param startDate
     * @param endDate
     * @return 天数
     */
    public static Long getIntersection(Date date, Date startDate, Date endDate)
    {
        int days = 0;
        int maxDay = DateUtils.toCalendar(date).get(Calendar.DAY_OF_MONTH);
        int month = DateUtils.toCalendar(date).get(Calendar.MONTH) + 1;
        int startMonth = DateUtils.toCalendar(startDate).get(Calendar.MONTH) + 1;
        int endDay = DateUtils.toCalendar(endDate).get(Calendar.DAY_OF_MONTH);
        int startDay = DateUtils.toCalendar(startDate).get(Calendar.DAY_OF_MONTH);
        int currentMonth = DateUtils.toCalendar(new Date()).get(Calendar.MONTH) + 1;
        if (currentMonth == month)
        {
            if (date.compareTo(endDate) < 0)
            {
                if (startMonth < month)
                {
                    days = days + maxDay - 1;
                }
                else
                {
                    days = days + maxDay - startDay + 1;
                }
            }
            else
            {
                if (startMonth < month)
                {
                    days = days + endDay - 1;
                }
                else
                {
                    days = days + endDay - startDay;
                }
            }
        }
        else
        {
            if (date.compareTo(endDate) < 0)
            {
                if (startMonth < month)
                {
                    days = days + maxDay;
                }
                else
                {
                    days = days + maxDay - startDay + 1;
                }
            }
            else
            {
                if (startMonth < month)
                {
                    days = days + endDay - 1;
                }
                else
                {
                    days = days + endDay - startDay;
                }
            }
        }
        return (long) days;
    }

    /**
     * 获取两个日期集合的交集 如日期"2015-08-05"同时在两个日期集合中 则获取该日期 （精确到日）
     * 
     * @return
     */
    public static List<Date> intersectDate(List<Date> oDate, List<Date> tDate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> listDate = new ArrayList<Date>();
        for (Date wDate : oDate)
        {
            for (Date iDate : tDate)
            {
                String strWDate = sdf.format(wDate).toString();
                String strIDate = sdf.format(iDate).toString();
                if (strWDate.equals(strIDate))
                {
                    listDate.add(wDate);
                }
            }
        }
        return listDate;
    }

    /**
     * 获取 在date月内 去掉具体的日期yyyy-MM-dd剩余日期的集合
     * 
     * @param date
     * @param listDate
     * @return 日期集合
     * @throws ParseException
     */
    public static List<Date> getSubtraction(Date date, List<Date> singleDate) throws ParseException
    {
        List<Date> listDate = new ArrayList<Date>();
        Calendar calendar = DateUtils.toCalendar(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int maxDay = calendar.get(Calendar.DAY_OF_MONTH);
        if (DateUtil.isEqualYM(date))
        {
            for (int day = 1; day < maxDay; day++)
            {
                Boolean isday = true;
                for (Date sDate : singleDate)
                {
                    if (DateUtils.toCalendar(sDate).get(Calendar.DAY_OF_MONTH) == day)
                    {
                        isday = false;
                    }
                }
                if (isday)
                {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date lDate = simpleDateFormat.parse(year + "-" + month + "-" + day);
                    listDate.add(lDate);
                }
            }
        }
        else
        {
            for (int day = 1; day <= maxDay; day++)
            {
                Boolean isday = true;
                for (Date sDate : singleDate)
                {
                    if (DateUtils.toCalendar(sDate).get(Calendar.DAY_OF_MONTH) == day)
                    {
                        isday = false;
                    }
                }
                if (isday)
                {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date lDate = simpleDateFormat.parse(year + "-" + month + "-" + day);
                    listDate.add(lDate);
                }
            }
        }
        return listDate;
    }

    /**
     * 获取日期集合中的所有日期 属于一个日期区间内的总数（精确到日）
     * 
     * @param startDate
     * @param endDate
     * @param specDate
     * @return 日期集合
     * @throws ParseException
     */
    public static int getContainCount(Date startDate, Date endDate, List<Date> specDate) throws ParseException
    {
        List<Date> dateList = new ArrayList<Date>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        startDate = sdf.parse(date2String(startDate, 10));
        endDate = sdf.parse(date2String(endDate, 10));
        for (Date date : specDate)
        {
            if (date.compareTo(startDate) >= 0 && date.compareTo(endDate) < 0)
            {
                dateList.add(date);
            }
        }
        return dateList.size();
    }

    /**
     * 日期和星期计算 如日期集合中有2015-08-05 这一天是星期三 在星期集合中存在星期三 ,则获取该日期
     * 
     * @return
     */
    public static List<Date> getSubDate(List<Integer> weeks, List<Date> listDate)
    {
        SimpleDateFormat format = new SimpleDateFormat(default_format);
        List<Date> weekSubDate = new ArrayList<Date>();
        for (Date date : listDate)
        {
            int dWeek = getWeek(format.format(date));
            Boolean isWeek = true;
            for (Integer week : weeks)
            {
                if (week == dWeek)
                {
                    isWeek = false;
                }
            }
            if (!isWeek)
            {
                weekSubDate.add(date);
            }
        }
        return weekSubDate;
    }

    /**
     * 根据一个日期字符串获取日期集合 如：字符串为"2015-08-01,2015-08-02,2015-08-03"
     * 
     * @param date
     * @param split
     * @return
     * @throws ParseException
     */
    public static List<Date> string2DateList(String SDate, String split) throws ParseException
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String[] dateArray = SDate.split(split);
        List<Date> dateList = new ArrayList<Date>();
        for (String date : dateArray)
        {
            dateList.add(simpleDateFormat.parse(date));
        }
        return dateList;
    }
}
