package com.sammyun.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 缓存系统中css/js
 * 
 * @author xutianlong
 * @version [版本号, Nov 20, 2014]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ResponseHeaderFilter implements Filter
{
    private FilterConfig fc;

    @SuppressWarnings("rawtypes")
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException
    {
        HttpServletResponse response = (HttpServletResponse) res;
        for (Enumeration e = this.fc.getInitParameterNames(); e.hasMoreElements();)
        {
            String headerName = (String) e.nextElement();
            response.addHeader(headerName, this.fc.getInitParameter(headerName));
        }
        chain.doFilter(req, response);
    }

    public void init(FilterConfig filterConfig)
    {
        this.fc = filterConfig;
    }

    public void destroy()
    {
        this.fc = null;
    }

}
