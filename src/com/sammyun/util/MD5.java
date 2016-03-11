/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Utils - MD5
 * 


 */
public class MD5
{

    public static final int OXFF = 0xff;

    public static final int OX10 = 0x10;

    /**
	 * 
	 */
    private MD5()
    {

    }

    /**
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String crypt(String str) throws NoSuchAlgorithmException
    {
        if (str == null || str.length() == 0)
        {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }

        StringBuffer hexString = new StringBuffer();

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());
        byte[] hash = md.digest();

        for (byte aHash : hash)
        {
            if ((OXFF & aHash) < OX10)
            {
                hexString.append("0").append(Integer.toHexString((OXFF & aHash)));
            }
            else
            {
                hexString.append(Integer.toHexString(OXFF & aHash));
            }
        }

        return hexString.toString();
    }

    /**
     * 签名字符串
     * 
     * @param text 需要签名的字符串
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String input_charset)
    {
        text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset)
    {
        if (charset == null || "".equals(charset))
        {
            return content.getBytes();
        }
        try
        {
            return content.getBytes(charset);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    /**
     * 签名字符串
     * 
     * @param text 需要签名的字符串
     * @param sign 签名结果
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key, String input_charset)
    {
        text = text + key;
        String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
        if (mysign.equals(sign))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
