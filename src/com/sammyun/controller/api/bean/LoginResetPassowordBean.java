package com.sammyun.controller.api.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "loginResetPassowordBean")
public class LoginResetPassowordBean
{
    /** 手机号 */
    private String phoneNumber;

    /** 新密码 */
    private String password;

    /** 安全key */
    private String safeKey;

    @XmlElement
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    @XmlElement
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @XmlElement
    public String getSafeKey()
    {
        return safeKey;
    }

    public void setSafeKey(String safeKey)
    {
        this.safeKey = safeKey;
    }

}
