package com.sammyun.controller.console;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.sammyun.FileInfo.FileType;
import com.sammyun.service.FileService;
import com.sammyun.util.JsonUtils;

/**
 * Controller - 文件上传
 * 
 * @author maxu
 * @TEL 13952522076
 */
@Controller("uploadController")
@RequestMapping("/console/upload")
public class UploadController extends BaseController
{
    @Resource(name = "fileServiceImpl")
    private FileService fileService;

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
    public void upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response)
    {
        Map<String, Object> data = new HashMap<String, Object>();
        String url = fileService.upload(FileType.file, file, false);
        if (url == null)
        {
            data.put("message", "fail");
        }
        else
        {
            data.put("message", "success");
            data.put("url", url);
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

}
