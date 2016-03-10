package com.sammyun.template.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sammyun.service.TagService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 模板指令 - 当前品牌ID
 * 
 * @author Sencloud Team

 */
@Component("tagsIdDirective")
public class TagsIdDirective extends BaseDirective
{

    /** 变量名称 */
    private static final String VARIABLE_NAME = "currentTagId";

    @Resource(name = "tagServiceImpl")
    private TagService tagService;

    @SuppressWarnings("rawtypes")
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException
    {
        Long tagId = tagService.getCurrentTagId();
        if (body != null && tagId != null)
        {
            setLocalVariable(VARIABLE_NAME, tagId.toString(), env, body);
        }
        else
        {
            if (tagId != null)
            {
                Writer out = env.getOut();
                out.write(tagId.toString());
            }
        }
    }
}
