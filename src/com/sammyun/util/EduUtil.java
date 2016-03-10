package com.sammyun.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sammyun.interceptor.ExecuteTimeInterceptor;
import com.sammyun.meta.Dto;

public class EduUtil
{
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ExecuteTimeInterceptor.class);

    /**
     * 判断对象是否Empty(null或元素为0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     * 
     * @param pObj 待检查对象
     * @return boolean 返回的布尔值
     */
    public static boolean isEmpty(Object pObj)
    {
        if (pObj == null)
            return true;
        if ("".equals(pObj))
            return true;
        if (pObj instanceof String)
        {
            if (((String) pObj).length() == 0)
            {
                return true;
            }
        }
        else if (pObj instanceof Collection)
        {
            if (((Collection) pObj).size() == 0)
            {
                return true;
            }
        }
        else if (pObj instanceof Map)
        {
            if (((Map) pObj).size() == 0)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断对象是否为NotEmpty(!null或元素>0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     * 
     * @param pObj 待检查对象
     * @return boolean 返回的布尔值
     */
    public static boolean isNotEmpty(Object pObj)
    {
        if (pObj == null)
            return false;
        if ("".equals(pObj.toString()))
            return false;
        if (pObj instanceof String)
        {
            if (((String) pObj).length() == 0)
            {
                return false;
            }
        }
        else if (pObj instanceof Collection)
        {
            if (((Collection) pObj).size() == 0)
            {
                return false;
            }
        }
        else if (pObj instanceof Map)
        {
            if (((Map) pObj).size() == 0)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断一个字符串是否由数字、字母、数字字母组成
     * 
     * @param pStr 需要判断的字符串
     * @param pStyle 判断规则
     * @return boolean 返回的布尔值
     */
    public static boolean isTheStyle(String pStr, String pStyle)
    {
        for (int i = 0; i < pStr.length(); i++)
        {
            char c = pStr.charAt(i);
            if (pStyle.equals(EduConstants.S_STYLE_N))
            {
                if (!Character.isDigit(c))
                    return false;
            }
            else if (pStyle.equals(EduConstants.S_STYLE_L))
            {
                if (!Character.isLetter(c))
                    return false;
            }
            else if (pStyle.equals(EduConstants.S_STYLE_NL))
            {
                if (Character.isLetterOrDigit(c))
                    return false;
            }
        }
        return true;
    }

    /**
     * JavaBean之间对象属性值拷贝
     * 
     * @param pFromObj Bean源对象
     * @param pToObj Bean目标对象
     */
    public static void copyPropBetweenBeans(Object pFromObj, Object pToObj)
    {
        if (pToObj != null)
        {
            try
            {
                BeanUtils.copyProperties(pToObj, pFromObj);
            }
            catch (Exception e)
            {
                logger.error("==开发人员请注意:==\n JavaBean之间的属性值拷贝发生错误啦!" + "\n详细错误信息如下:");
                e.printStackTrace();
            }
        }
    }

    /**
     * 将JavaBean对象中的属性值拷贝到Dto对象
     * 
     * @param pFromObj JavaBean对象源
     * @param pToDto Dto目标对象
     */
    public static void copyPropFromBean2Dto(Object pFromObj, Dto pToDto)
    {
        if (pToDto != null)
        {
            try
            {
                pToDto.putAll(BeanUtils.describe(pFromObj));
                // BeanUtils自动加入了一个Key为class的键值,故将其移除
                pToDto.remove("class");
            }
            catch (Exception e)
            {
                logger.error("==开发人员请注意:==\n 将JavaBean属性值拷贝到Dto对象发生错误啦!" + "\n详细错误信息如下:");
                e.printStackTrace();
            }
        }
    }

    /**
     * 实现字符串首字母大写
     * 
     * @param str 字符串(任意字符串)
     * @return String 首字母大写
     */
    public static String upperCaseFirstChar(String str)
    {
        str = str.toLowerCase();// 先转换成小写
        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        return str;
    }

    /**
     * 获取ip地址,防止集群、代理
     * 
     * @param request
     * @return ip
     */
    public static String getAddr(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * String 转成Long数组<一句话功能简述> <功能详细描述>
     * 
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Long[] string2Long(String str)
    {
        if ("".equals(str) || null == str)
        {
            return null;
        }
        String[] strArray = str.split(",");
        Long[] longArray = new Long[strArray.length];
        for (int i = 0; i < strArray.length; i++)
        {
            longArray[i] = Long.valueOf(strArray[i]);
        }
        return longArray;
    }

    /**
     * String 转成BigDeciaml集合<一句话功能简述> <功能详细描述>
     * 
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static List<BigDecimal> string2BigDecimal(String str)
    {
        if ("".equals(str) || null == str)
        {
            return null;
        }
        String[] strArray = str.split(",");
        List<BigDecimal> bigDecimalList = new ArrayList<BigDecimal>();
        for (int i = 0; i < strArray.length; i++)
        {
            BigDecimal bigDecimal = new BigDecimal(strArray[i]);
            bigDecimalList.add(bigDecimal);
        }
        return bigDecimalList;
    }

    /**
     * 验证电话号码是否正确
     * 
     * @param mobiles
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isMobile(String mobiles)
    {
        Pattern p = Pattern.compile("^1[345678]\\d{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 验证是否是邮件格式<一句话功能简述> <功能详细描述>
     * 
     * @param email
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isEmail(String email)
    {
        String str = "^([a-zA-Z0-9]*([-_]|[-.])?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 计算Map合
     * 
     * @param totalMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static BigDecimal totalMap(Map<Long, BigDecimal> totalMap)
    {
        BigDecimal bd = new BigDecimal(0);
        for (Long key : totalMap.keySet())
        {
            bd = bd.add(totalMap.get(key));
        }
        return bd;
    }

    /**
     * 下载 文件
     * 
     * @param request
     * @param response
     * @param storeName
     * @param contentType
     * @param realName
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public static void download(HttpServletRequest request, HttpServletResponse response, String downLoadPath,
            String contentType, String realName) throws Exception
    {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        String ctxPath = request.getSession().getServletContext().getRealPath("/") + downLoadPath;
        long fileLength = new File(ctxPath).length();

        response.setContentType(contentType);
        response.setHeader("Content-disposition", "attachment; filename="
                + new String(realName.getBytes("utf-8"), "ISO8859-1"));
        response.setHeader("Content-Length", String.valueOf(fileLength));

        bis = new BufferedInputStream(new FileInputStream(ctxPath));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
        {
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();
    }
}
