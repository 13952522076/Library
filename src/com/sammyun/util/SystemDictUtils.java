package com.sammyun.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.eclipse.jdt.internal.compiler.ast.ThisReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sammyun.CommonAttributes;
import com.sammyun.EnumConverter;
import com.sammyun.entity.system.SystemDict;
import com.sammyun.service.PluginService;
import com.sammyun.service.system.SystemDictService;

/**
 * 系统字典维护，从数据库中读取出来，存入内存中，<br />
 * 内存中存在，取内存里面<br />
 * 不存在，重新读取数据库中，再存入内存
 * 
 * @author xutianlong
 * @version [版本号, Jul 10, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@SuppressWarnings("unchecked")
public class SystemDictUtils
{

    public static final Logger logger = LoggerFactory.getLogger(SystemDictUtils.class);

    /** 缓存管理 */
    private static final CacheManager cacheManager = CacheManager.create();

    /** BeanUtilsBean */
    private static final BeanUtilsBean beanUtils;

    /** 不可以实例 */
    private SystemDictUtils()
    {
    }

    static
    {
        ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean()
        {

            @Override
            public String convert(Object value)
            {
                if (value != null)
                {
                    Class<?> type = value.getClass();
                    if (type.isEnum() && super.lookup(type) == null)
                    {
                        super.register(new EnumConverter(type), type);
                    }
                    else if (type.isArray() && type.getComponentType().isEnum())
                    {
                        if (super.lookup(type) == null)
                        {
                            ArrayConverter arrayConverter = new ArrayConverter(type, new EnumConverter(
                                    type.getComponentType()), 0);
                            arrayConverter.setOnlyFirstToString(false);
                            super.register(arrayConverter, type);
                        }
                        Converter converter = super.lookup(type);
                        return ((String) converter.convert(String.class, value));
                    }
                }
                return super.convert(value);
            }

            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(String value, Class clazz)
            {
                if (clazz.isEnum() && super.lookup(clazz) == null)
                {
                    super.register(new EnumConverter(clazz), clazz);
                }
                return super.convert(value, clazz);
            }

            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(String[] values, Class clazz)
            {
                if (clazz.isArray() && clazz.getComponentType().isEnum()
                        && super.lookup(clazz.getComponentType()) == null)
                {
                    super.register(new EnumConverter(clazz.getComponentType()), clazz.getComponentType());
                }
                return super.convert(values, clazz);
            }

            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(Object value, Class targetType)
            {
                if (super.lookup(targetType) == null)
                {
                    if (targetType.isEnum())
                    {
                        super.register(new EnumConverter(targetType), targetType);
                    }
                    else if (targetType.isArray() && targetType.getComponentType().isEnum())
                    {
                        ArrayConverter arrayConverter = new ArrayConverter(targetType, new EnumConverter(
                                targetType.getComponentType()), 0);
                        arrayConverter.setOnlyFirstToString(false);
                        super.register(arrayConverter, targetType);
                    }
                }
                return super.convert(value, targetType);
            }
        };

        DateConverter dateConverter = new DateConverter();
        dateConverter.setPatterns(CommonAttributes.DATE_PATTERNS);
        convertUtilsBean.register(dateConverter, Date.class);
        beanUtils = new BeanUtilsBean(convertUtilsBean);
    }

    /**
     * 获取系统字典
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Map<String, Map<String, String>> get()
    {
        Ehcache cache = cacheManager.getEhcache(SystemDict.CACHE_NAME);
        net.sf.ehcache.Element cacheElement = cache.get(SystemDict.CACHE_KEY);
        Map<String, Map<String, String>> systemDictLargeMap = null;
        if (cacheElement != null)
        {
            systemDictLargeMap = (Map<String, Map<String, String>>) cacheElement.getObjectValue();
        }
        return systemDictLargeMap;
    }

    /**
     * 过滤敏感词 <功能详细描述>
     * 
     * @param diaryMsg
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String changeSensitiveWords(String diaryMsg)
    {
        if (diaryMsg == null || diaryMsg.equals(""))
        {
            return diaryMsg;
        }
        Map<String, Map<String, String>> bigMap = get();
        Map<String, String> sensitiveMap = bigMap.get("0");// 0是数据库后台规定的敏感词的
        if (sensitiveMap == null || sensitiveMap.size() == 0)
        {
            return diaryMsg;
        }
        List<String> sensitiveWords = new ArrayList<String>();
        for (Map.Entry<String, String> entry : sensitiveMap.entrySet())
        {
            sensitiveWords.add(entry.getValue());
        }
        for (String sensitiveWord : sensitiveWords)
        {
            diaryMsg = diaryMsg.replace(sensitiveWord, "**");
        }
        return diaryMsg;
    }
}
