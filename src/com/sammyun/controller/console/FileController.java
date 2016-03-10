/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sammyun.FileInfo;
import com.sammyun.FileInfo.FileType;
import com.sammyun.FileInfo.OrderType;
import com.sammyun.Message;
import com.sammyun.service.FileService;
import com.sammyun.util.JsonUtils;

/**
 * Controller - 文件处理
 * 
 * @author Sencloud Team

 */
@Controller("adminFileController")
@RequestMapping("/console/file")
public class FileController extends BaseController
{

    @Resource(name = "fileServiceImpl")
    private FileService fileService;
    
    /**
     * 初始化长宽取值
     */
    private static Integer INIT_VALUE = -1;

    /**
     * 上传
     * 
     * @throws IOException
     */
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public void upload(FileType fileType, MultipartFile file,
			HttpServletResponse response, Integer imageWidth,
			Integer imageHeight)
    {
        Map<String, Object> data = new HashMap<String, Object>();
        if (!fileService.isValid(fileType, file))
        {
            data.put("message", Message.warn("console.upload.invalid"));
        }
        else
        {
        	//add by toncho 20150813 start
        	int width = INIT_VALUE; // 原始图片宽，初始化值－1
	        int height = INIT_VALUE; // 原始图片高，初始化值－1
        	if(imageWidth != null && imageHeight != null){
            	/** 写文件前先读出图片原始高宽 **/
    			try {
					byte[] bytes = file.getBytes();
					InputStream is = new ByteArrayInputStream(bytes);
					BufferedImage bufimg = ImageIO.read(is);
					// 只有图片才获取高宽
					if (bufimg != null) {
					    width = bufimg.getWidth();
					    height = bufimg.getHeight();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	
			if (width != INIT_VALUE && height != INIT_VALUE
					&& (width != imageWidth || height != imageHeight)) {
				data.put("sizeErrorMessage", Message.warn("上传的图片的尺寸有误！"));
			}
			else{
				data.put("sizeErrorMessage", SUCCESS_MESSAGE);
			}
        	
        	String url = fileService.upload(fileType, file, false);
            if (url == null)
            {
                data.put("message", Message.warn("console.upload.error"));
            }
            else
            {
                data.put("message", SUCCESS_MESSAGE);
                data.put("url", url);
            }
         	//add by toncho 20150813 end
        }
        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), data);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 浏览
     */
    @RequestMapping(value = "/browser", method = RequestMethod.GET)
    public @ResponseBody
    List<FileInfo> browser(String path, FileType fileType, OrderType orderType)
    {
        return fileService.browser(path, fileType, orderType);
    }

}
