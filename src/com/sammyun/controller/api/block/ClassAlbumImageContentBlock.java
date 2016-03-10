package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "classAlbumImageContentBlock")
public class ClassAlbumImageContentBlock
{
    private Long id;
    
    /** 图片附件 */
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
