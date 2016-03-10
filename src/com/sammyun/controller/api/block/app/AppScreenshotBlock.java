package com.sammyun.controller.api.block.app;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "appScreenshotBlock")
public class AppScreenshotBlock
{
    /** 截图名 */
    private String screenshot;

    /** 截图地址 */
    private String screenshotUrl;

    @XmlElement
    public String getScreenshot()
    {
        return screenshot;
    }

    public void setScreenshot(String screenshot)
    {
        this.screenshot = screenshot;
    }

    @XmlElement
    public String getScreenshotUrl()
    {
        return screenshotUrl;
    }

    public void setScreenshotUrl(String screenshotUrl)
    {
        this.screenshotUrl = screenshotUrl;
    }

}
