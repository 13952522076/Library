package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "campusviewImgAlbumsAttachBlock")
public class CampusviewImgAlbumsAttachBlock
{
    private Long id;
    
    /** 校园风光表 */
    private String imageAttach;
    
    @XmlElement
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @XmlElement
    public String getImageAttach()
    {
        return imageAttach;
    }

    public void setImageAttach(String imageAttach)
    {
        this.imageAttach = imageAttach;
    }
    
   
}
