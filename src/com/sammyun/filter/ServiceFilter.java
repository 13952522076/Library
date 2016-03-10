package com.sammyun.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

public class ServiceFilter extends OncePerRequestFilter
{

    /** 默认忽略URL */
    private static final String[] DEFAULT_ROUTER_URL_PATTERNS = new String[] {"/router/**"};

    /** antPathMatcher */
    private static AntPathMatcher antPathMatcher = new AntPathMatcher();

    /** 忽略URL */
    private String[] routerUrlPatterns = DEFAULT_ROUTER_URL_PATTERNS;

    /** 错误消息 */
    private static final String ERROR_MESSAGE = "Access denied!";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {

        String path = request.getServletPath();

        if (antPathMatcher.match("/router/erpApi/**", path))
        {
            filterChain.doFilter(request, response);
        }
        else
        {
            if (routerUrlPatterns != null)
            {
                for (String ignoreUrlPattern : routerUrlPatterns)
                {
                    if (antPathMatcher.match(ignoreUrlPattern, path))
                    {
                        String deviceName = request.getHeader("deviceName");
                        String deviceModelID = request.getHeader("deviceModelID");
                        String deviceModel = request.getHeader("deviceModel");
                        String deviceOs = request.getHeader("deviceOs");
                        String osVersion = request.getHeader("osVersion");
                        if (!(null != deviceName && null != deviceModelID && null != deviceModel && null != deviceOs && null != osVersion))
                        {
                            response.sendError(HttpServletResponse.SC_FORBIDDEN, ERROR_MESSAGE);
                        }
                        else
                        {
                            filterChain.doFilter(request, response);
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取忽略URL
     * 
     * @return 忽略URL
     */
    public String[] getIgnoreUrlPatterns()
    {
        return routerUrlPatterns;
    }

    /**
     * 设置忽略URL
     * 
     * @param ignoreUrlPatterns 忽略URL
     */
    public void setIgnoreUrlPatterns(String[] ignoreUrlPatterns)
    {
        this.routerUrlPatterns = ignoreUrlPatterns;
    }

}
