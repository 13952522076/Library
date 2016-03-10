package com.sammyun.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * jackson 事件格式化序列 使用方式：@JsonSerialize(using=JsonDateSerializer.class)
 * 在get器上使用
 * 
 * @author xutianlong
 */
public class JsonDateSerializer extends JsonSerializer<Date>
{
    @Override
    public void serialize(Date arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException,
            JsonProcessingException
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(arg0);
        arg1.writeString(formattedDate);

    }
}
