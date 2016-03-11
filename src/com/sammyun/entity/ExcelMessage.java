/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.entity;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity - 排序基类
 * 


 */
@MappedSuperclass
public class ExcelMessage
{
    public enum Status
    {

        /** 成功 */
        success,

        /** 失败 */
        fail
    }

    /** 返回错误消息 */
    private String error;

    /** 返回正确消息 */
    private String ok;

    /** 返回消息状态 */
    private Status status;

    /** 返回的url */
    private String url;

    /**
     * 获得错误信息
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    @JsonProperty
    public String getError()
    {
        return error;
    }

    /**
     * 设置错误信息 <功能详细描述>
     * 
     * @param error
     * @see [类、类#方法、类#成员]
     */
    public void setError(String error)
    {
        this.error = error;
    }

    /**
     * 获得正确信息 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    @JsonProperty
    public String getOk()
    {
        return ok;
    }

    /**
     * 设置正确信息 <功能详细描述>
     * 
     * @param ok
     * @see [类、类#方法、类#成员]
     */
    public void setOk(String ok)
    {
        this.ok = ok;
    }

    /**
     * 获得状态 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Status getStatus()
    {
        return status;
    }

    /**
     * 设置状态 <功能详细描述>
     * 
     * @param status
     * @see [类、类#方法、类#成员]
     */
    public void setStatus(Status status)
    {
        this.status = status;
    }

    /**
     * 获得返回的url <功能详细描述>
     * 
     * @param status
     * @see [类、类#方法、类#成员]
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * 设置返回的url <功能详细描述>
     * 
     * @param status
     * @see [类、类#方法、类#成员]
     */
    public void setUrl(String url)
    {
        this.url = url;
    }

}
