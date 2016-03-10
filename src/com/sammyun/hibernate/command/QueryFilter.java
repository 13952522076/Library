/**
 * 全局App条件封装类
 */

package com.sammyun.hibernate.command;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sammyun.Pageable;
import com.sammyun.util.ParamUtil;

/**
 * 全局条件封装类
 * 
 * @author xutianlong
 * @version [版本号, Apr 9, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class QueryFilter
{
    private boolean isExport = false;

    public static final String ORDER_DESC = "desc";

    public static final String ORDER_ASC = "asc";

    public static final Log logger = LogFactory.getLog(QueryFilter.class);

    private HttpServletRequest request = null;

    private String filterName = null;

    private List<Object> paramValues = new ArrayList<Object>();

    private List<CriteriaCommand> commands = new ArrayList<CriteriaCommand>();

    private Set<String> aliasSet = new HashSet<String>();

    private Pageable pageable = null;

    public String getFilterName()
    {
        return this.filterName;
    }

    public void setFilterName(String filterName)
    {
        this.filterName = filterName;
    }

    public Pageable getPageable()
    {
        return this.pageable;
    }

    public QueryFilter()
    {
    }

    public QueryFilter(Pageable pagingBean)
    {
        this.pageable = pagingBean;
    }

    /**
     * 解析request 提交函数中的值<br />
     * <默认构造函数>
     */
    public QueryFilter(HttpServletRequest request)
    {
        this.request = request;
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

        Integer start = Integer.valueOf(0);
        Integer limit = Pageable.DEFAULT_PAGE_SIZE;

        String s_start = request.getParameter("start");
        String s_limit = request.getParameter("limit");
        if (StringUtils.isNotEmpty(s_start))
        {
            start = new Integer(s_start);
        }
        if (StringUtils.isNotEmpty(s_limit))
        {
            limit = new Integer(s_limit);
        }

        String sort = request.getParameter("sort");
        String dir = request.getParameter("dir");

        if ((StringUtils.isNotEmpty(sort)) && (StringUtils.isNotEmpty(dir)))
        {
            addSorted(sort, dir);
        }

        if ("true".equals(request.getParameter("isExport")))
        {
            this.isExport = true;
            request.setAttribute("colId", request.getParameter("colId"));
            request.setAttribute("colName", request.getParameter("colName"));
            request.setAttribute("exportType", request.getParameter("exportType"));
        }
        request.setAttribute("isExport", Boolean.valueOf(this.isExport));

        this.pageable = new Pageable(start.intValue(), limit.intValue());
    }

    /**
     * 添加查询参数
     * 
     * @param paramName 参数名
     * @param paramValue 参数值
     * @see [类、类#方法、类#成员]
     */
    public void addFilter(String paramName, String paramValue)
    {
        String[] fieldInfo = paramName.split("[_]");
        Object value = null;
        if ((fieldInfo != null) && (fieldInfo.length == 4))
        {
            value = ParamUtil.convertObject(fieldInfo[2], paramValue);
            if (value != null)
            {
                FieldCommandImpl fieldCommand = new FieldCommandImpl(fieldInfo[1], value, fieldInfo[3], this);
                this.commands.add(fieldCommand);
            }
        }
        else if ((fieldInfo != null) && (fieldInfo.length == 3))
        {
            FieldCommandImpl fieldCommand = new FieldCommandImpl(fieldInfo[1], value, fieldInfo[2], this);
            this.commands.add(fieldCommand);
        }
        else
        {
            logger.error("Query param name [" + paramName + "] is not right format.");
        }
    }

    public void addParamValue(Object value)
    {
        this.paramValues.add(value);
    }

    public List getParamValueList()
    {
        return this.paramValues;
    }

    public void addSorted(String orderBy, String ascDesc)
    {
        this.commands.add(new SortCommandImpl(orderBy, ascDesc, this));
    }

    public void addExample(Object object)
    {
        this.commands.add(new ExampleCommandImpl(object));
    }

    public List<CriteriaCommand> getCommands()
    {
        return this.commands;
    }

    public Set<String> getAliasSet()
    {
        return this.aliasSet;
    }

    public boolean isExport()
    {
        return this.isExport;
    }

    public void setExport(boolean isExport)
    {
        this.isExport = isExport;
    }

    public HttpServletRequest getRequest()
    {
        return this.request;
    }
}
