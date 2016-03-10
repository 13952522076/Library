package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "growthDiaryFileBlock")
public class GrowthDiaryFileBlock
{

    /** 文件类型 */
    private String fileType;

    /** 文件路径 */
    private String fileUrl;

    @XmlElement
    public String getFileType()
    {
        return fileType;
    }

    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }

    @XmlElement
    public String getFileUrl()
    {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl)
    {
        this.fileUrl = fileUrl;
    }

}
