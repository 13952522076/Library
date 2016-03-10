package com.sammyun.controller.api.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "loginBean")
public class LoginBean
{
    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 设备信息 */
    private MemberDeviceInfoBean memberDeviceInfoBean;

    @XmlElement
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
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
    public MemberDeviceInfoBean getMemberDeviceInfoBean()
    {
        return memberDeviceInfoBean;
    }

    public void setMemberDeviceInfoBean(MemberDeviceInfoBean memberDeviceInfoBean)
    {
        this.memberDeviceInfoBean = memberDeviceInfoBean;
    }

}
