package com.sammyun.hibernate.command;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sammyun.util.ParamUtil;

/**
 * 报表过滤器
 * 
 * @author xutianlong
 * @version [版本号, Apr 9, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ReportFilter
{
    Map<String, Object> variables = new HashMap<String, Object>();

    public ReportFilter()
    {
    }

    @SuppressWarnings("rawtypes")
    public ReportFilter(HttpServletRequest request)
    {
        Enumeration paramEnu = request.getParameterNames();
        while (paramEnu.hasMoreElements())
        {
            String paramName = (String) paramEnu.nextElement();

            if (paramName.startsWith("Q_"))
            {
                String paramValue = request.getParameter(paramName);
                addFilter(paramName, paramValue);
            }
        }
    }

    public void addFilter(String paramName, String value)
    {
        String[] fieldInfo = paramName.split("[_]");
        if (fieldInfo.length == 3)
            this.variables.put(fieldInfo[1], ParamUtil.convertObject(fieldInfo[2], value));
    }

    public Map<String, Object> getVariables()
    {
        return this.variables;
    }

    public void setVariables(Map<String, Object> variables)
    {
        this.variables = variables;
    }
}
