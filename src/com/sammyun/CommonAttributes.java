/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun;

/**
 * 公共参数
 * 
 * @author Sencloud Team

 */
public final class CommonAttributes
{

    /** 日期格式配比 */
    public static final String[] DATE_PATTERNS = new String[] {"yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd",
            "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss"};

    /** preschoolEdu.xml文件路径 */
    public static final String PRESCHOOLEDU_XML_PATH = "/preschoolEdu.xml";

    /** preschoolEdu.properties文件路径 */
    public static final String PRESCHOOLEDU_PROPERTIES_PATH = "/preschoolEdu.properties";

    /** 颜色规格 */
    public static final String COLOR = "color";

    /** size规格 */
    public static final String SIZE = "size";

    /** 鞋子size规格 */
    public static final String SHOES_SIZE = "shoes_size";

    /** 裤子size规格 */
    public static final String PANTS_SIZE = "pants_size";

    /** preschoolEdu.properties文件路径 */
    public static final String PRESCHOOLEDU_ERP_PROPERTIES_PATH = "/global.sc.properties";

    /**
     * 不可实例化
     */
    private CommonAttributes()
    {
    }

}
