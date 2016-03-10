/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.client.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpRequester - HTTP请求对象
 * 
 * @author xutianlong
 * @version 3.0
 */
public class HttpRequester
{

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(HttpRequester.class);

    private String defaultContentEncoding;

    public HttpRequester()
    {
        this.defaultContentEncoding = Charset.defaultCharset().name();
    }

    /**
     * 发送GET请求
     * 
     * @param urlString URL地址
     * @return 响应对象
     * @throws IOException
     */
    public HttpRespons sendGet(String urlString) throws IOException
    {
        return this.send(urlString, "GET", null, null);
    }

    /**
     * 发送GET请求
     * 
     * @param urlString URL地址
     * @param params 参数集合
     * @return 响应对象
     * @throws IOException
     */
    public HttpRespons sendGet(String urlString, Map<String, String> params) throws IOException
    {
        return this.send(urlString, "GET", params, null);
    }

    /**
     * 发送GET请求
     * 
     * @param urlString URL地址
     * @param params 参数集合
     * @return 响应对象
     * @throws IOException
     */
    public HttpRespons sendGet(String urlString, String params) throws IOException
    {
        return this.sendJsonParam(urlString, "GET", params, null);
    }

    /**
     * 发送GET请求
     * 
     * @param urlString URL地址
     * @param params 参数集合
     * @param propertys 请求属性
     * @return 响应对象
     * @throws IOException
     */
    public HttpRespons sendGet(String urlString, Map<String, String> params, Map<String, String> propertys)
            throws IOException
    {
        return this.send(urlString, "GET", params, propertys);
    }

    /**
     * 发送GET请求
     * 
     * @param urlString URL地址
     * @param params 参数集合
     * @param propertys 请求属性
     * @return 响应对象
     * @throws IOException
     */
    public HttpRespons sendGet(String urlString, String params, Map<String, String> propertys) throws IOException
    {
        return this.sendJsonParam(urlString, "GET", params, propertys);
    }

    /**
     * 发送POST请求
     * 
     * @param urlString URL地址
     * @return 响应对象
     * @throws IOException
     */
    public HttpRespons sendPost(String urlString) throws IOException
    {
        return this.send(urlString, "POST", null, null);
    }

    /**
     * 发送POST请求
     * 
     * @param urlString URL地址
     * @param params 参数集合
     * @return 响应对象
     * @throws IOException
     */
    public HttpRespons sendPost(String urlString, Map<String, String> params) throws IOException
    {
        return this.send(urlString, "POST", params, null);
    }

    /**
     * 发送POST请求
     * 
     * @param urlString URL地址
     * @param params 参数集合
     * @return 响应对象
     * @throws IOException
     */
    public HttpRespons sendPost(String urlString, String params) throws IOException
    {
        return this.sendJsonParam(urlString, "POST", params, null);
    }

    /**
     * 发送POST请求
     * 
     * @param urlString URL地址
     * @param params 参数集合
     * @param propertys 请求属性
     * @return 响应对象
     * @throws IOException
     */
    public HttpRespons sendPost(String urlString, Map<String, String> params, Map<String, String> propertys)
            throws IOException
    {
        return this.send(urlString, "POST", params, propertys);
    }

    /**
     * 发送POST请求
     * 
     * @param urlString URL地址
     * @param params 参数集合
     * @param propertys 请求属性
     * @return 响应对象
     * @throws IOException
     */
    public HttpRespons sendPost(String urlString, String params, Map<String, String> propertys) throws IOException
    {
        return this.sendJsonParam(urlString, "POST", params, propertys);
    }

    /**
     * 发送HTTP请求
     * 
     * @param urlString
     * @return 响映对象
     * @throws IOException
     */
    private HttpRespons send(String urlString, String method, Map<String, String> parameters,
            Map<String, String> propertys) throws IOException
    {
        HttpURLConnection urlConnection = null;

        if (method.equalsIgnoreCase("GET") && parameters != null)
        {
            StringBuffer param = new StringBuffer();
            int i = 0;
            for (String key : parameters.keySet())
            {
                if (i == 0)
                    param.append("?");
                else
                    param.append("&");
                param.append(key).append("=").append(parameters.get(key));
                i++;
            }
            urlString += param;
        }
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod(method);
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);

        if (propertys != null)
            for (String key : propertys.keySet())
            {
                urlConnection.addRequestProperty(key, propertys.get(key));
            }

        if (method.equalsIgnoreCase("POST") && parameters != null)
        {
            StringBuffer param = new StringBuffer();
            for (String key : parameters.keySet())
            {
                param.append("&");
                param.append(key).append("=").append(parameters.get(key));
            }
            urlConnection.getOutputStream().write(param.toString().getBytes());
            urlConnection.getOutputStream().flush();
            urlConnection.getOutputStream().close();
        }

        return this.makeContent(urlString, urlConnection);
    }

    /**
     * 发送HTTP请求
     * 
     * @param urlString
     * @return 响映对象
     * @throws IOException
     */
    private HttpRespons sendJsonParam(String urlString, String method, String parameters, Map<String, String> propertys)
            throws IOException
    {
        HttpURLConnection urlConnection = null;
        parameters = parameters.substring(1, parameters.length() - 1);
        if (method.equalsIgnoreCase("GET") && parameters != null)
        {
            StringBuffer param = new StringBuffer();
            param.append("?param=");
            param.append(parameters);
            urlString += param;
        }
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod(method);
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);
        // urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

        if (propertys != null)
            for (String key : propertys.keySet())
            {
                urlConnection.addRequestProperty(key, propertys.get(key));
            }

        if (method.equalsIgnoreCase("POST") && parameters != null)
        {
            urlConnection.getOutputStream().write(parameters.toString().getBytes());
            urlConnection.getOutputStream().flush();
            urlConnection.getOutputStream().close();
        }

        return this.makeContent(urlString, urlConnection);
    }

    /**
     * 得到响应对象
     * 
     * @param urlConnection
     * @return 响应对象
     * @throws IOException
     */
    private HttpRespons makeContent(String urlString, HttpURLConnection urlConnection) throws IOException
    {
        HttpRespons httpResponser = new HttpRespons();
        try
        {
            InputStream in = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            httpResponser.contentCollection = new Vector<String>();
            StringBuffer temp = new StringBuffer();
            String line = bufferedReader.readLine();
            while (line != null)
            {
                httpResponser.contentCollection.add(line);
                temp.append(line).append("\r\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            String ecod = urlConnection.getContentEncoding();
            if (ecod == null)
            {
                ecod = "UTF-8";
            }
            httpResponser.urlString = urlString;
            httpResponser.defaultPort = urlConnection.getURL().getDefaultPort();
            httpResponser.file = urlConnection.getURL().getFile();
            httpResponser.host = urlConnection.getURL().getHost();
            httpResponser.path = urlConnection.getURL().getPath();
            httpResponser.port = urlConnection.getURL().getPort();
            httpResponser.protocol = urlConnection.getURL().getProtocol();
            httpResponser.query = urlConnection.getURL().getQuery();
            httpResponser.ref = urlConnection.getURL().getRef();
            httpResponser.userInfo = urlConnection.getURL().getUserInfo();
            httpResponser.content = temp.toString();// new
                                                    // String(temp.toString().getBytes(),
                                                    // "UTF-8");
            httpResponser.contentEncoding = ecod;
            httpResponser.code = urlConnection.getResponseCode();
            httpResponser.message = urlConnection.getResponseMessage();
            httpResponser.contentType = urlConnection.getContentType();
            httpResponser.method = urlConnection.getRequestMethod();
            httpResponser.connectTimeout = urlConnection.getConnectTimeout();
            httpResponser.readTimeout = urlConnection.getReadTimeout();

            return httpResponser;
        }
        catch (IOException e)
        {
            logger.error(e.getMessage());
            throw e;
        }
        finally
        {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
    }

    /**
     * 默认的响应字符集
     */
    public String getDefaultContentEncoding()
    {
        return this.defaultContentEncoding;
    }

    /**
     * 设置默认的响应字符集
     */
    public void setDefaultContentEncoding(String defaultContentEncoding)
    {
        this.defaultContentEncoding = defaultContentEncoding;
    }

}
