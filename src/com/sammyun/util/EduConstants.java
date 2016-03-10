/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.util;

/**
 * 常量表
 * 
 * @author xutianlong
 * @since 2014-06-07
 */
public interface EduConstants
{

    /**
     * XML文档风格<br>
     * 0:节点属性值方式
     */
    public static final String XML_Attribute = "0";

    /**
     * XML文档风格<br>
     * 1:节点元素值方式
     */
    public static final String XML_Node = "1";

    /**
     * 字符串组成类型<br>
     * number:数字字符串
     */
    public static final String S_STYLE_N = "number";

    /**
     * 字符串组成类型<br>
     * letter:字母字符串
     */
    public static final String S_STYLE_L = "letter";

    /**
     * 字符串组成类型<br>
     * numberletter:数字字母混合字符串
     */
    public static final String S_STYLE_NL = "numberletter";

    /**
     * 格式化(24小时制)<br>
     * FORMAT_DateTime: 日期时间
     */
    public static final String FORMAT_DateTime = "yyyy-MM-dd HH:mm:ss";

    /**
     * excel 模板地址
     */
    public static final String excelTemplate = "/excelTemplate/";
}
