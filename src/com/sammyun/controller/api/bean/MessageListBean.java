package com.sammyun.controller.api.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.message.Message.MessageCategory;

@XmlRootElement(name = "messageListBean")
public class MessageListBean
{
   
    /** 类型 */
    private String type;
    
    /** 学校id */
    private Long dictSchoolId;
    
    /** 会员id */
    private Long memberId;
    
    /** 分页信息 */
    private PageModel page;

    @XmlElement
    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @XmlElement
    public Long getDictSchoolId()
    {
        return dictSchoolId;
    }

    public void setDictSchoolId(Long dictSchoolId)
    {
        this.dictSchoolId = dictSchoolId;
    }

    @XmlElement
    public Long getMemberId()
    {
        return memberId;
    }

    public void setMemberId(Long memberId)
    {
        this.memberId = memberId;
    }

    @XmlElement
    public PageModel getPage()
    {
        return page;
    }

    public void setPage(PageModel page)
    {
        this.page = page;
    }
    
    
}
