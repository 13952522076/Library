package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "loginRequestVCodeBlock")
public class LoginRequestVCodeBlock
{
    /** 手机号 */
    private String phoneNumber;

    /** 验证码 */
    private String validateCode;

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
    public String getValidateCode()
    {
        return validateCode;
    }

    public void setValidateCode(String validateCode)
    {
        this.validateCode = validateCode;
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
