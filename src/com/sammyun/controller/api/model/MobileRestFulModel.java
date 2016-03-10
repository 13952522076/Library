package com.sammyun.controller.api.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.sammyun.controller.api.block.AnnouncementContentBlock;
import com.sammyun.controller.api.block.AttendanceEquipmentListV2Block;
import com.sammyun.controller.api.block.DiaryTagListBlock;
import com.sammyun.controller.api.block.LoginBlock;
import com.sammyun.controller.api.block.LoginRequestVCodeBlock;
import com.sammyun.controller.api.block.NewsContentBlock;
import com.sammyun.controller.api.block.PersonalPersonalInfoBlock;
import com.sammyun.controller.api.block.ProfileSchoolInfoBlock;
import com.sammyun.controller.api.block.app.AppStatBlock;

@XmlRootElement(name = "loginRestFulModel")
@XmlSeeAlso({LoginBlock.class, LoginRequestVCodeBlock.class, PersonalPersonalInfoBlock.class, NewsContentBlock.class,
        ProfileSchoolInfoBlock.class, AnnouncementContentBlock.class, AttendanceEquipmentListV2Block.class,
        DiaryTagListBlock.class, AppStatBlock.class})
public class MobileRestFulModel
{
    /** 错误编码 */
    private Integer resultCode;

    /** 错误信息 */
    private String resultMessage;

    /** 返回数据 */
    private Object rows;

    @XmlElement
    public Integer getResultCode()
    {
        return resultCode;
    }

    public void setResultCode(Integer resultCode)
    {
        this.resultCode = resultCode;
    }

    @XmlElement
    public String getResultMessage()
    {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage)
    {
        this.resultMessage = resultMessage;
    }

    @XmlElement
    public Object getRows()
    {
        return rows;
    }

    public void setRows(Object rows)
    {
        this.rows = rows;
    }

}
