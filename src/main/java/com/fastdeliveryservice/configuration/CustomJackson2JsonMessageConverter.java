package com.fastdeliveryservice.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;

import java.io.IOException;

/**
 * Created by strom on 27/09/2017.
 */
public class CustomJackson2JsonMessageConverter extends Jackson2JsonMessageConverter {

    @Override
    protected Message createMessage(Object objectToConvert, MessageProperties messageProperties)
            throws MessageConversionException {
        ObjectMapper mapper = new ObjectMapper();
        byte[] bytes;

        try
        {
            String jsonString = mapper
                    .writeValueAsString(objectToConvert);
            bytes = jsonString.getBytes(getDefaultCharset());
        }
        catch (IOException e) {
            throw new MessageConversionException(
                    "Failed to convert Message content", e);
        }

        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        messageProperties.setContentEncoding(getDefaultCharset());
        messageProperties.setContentLength(bytes.length);
        CustomDefaultJackson2JavaTypeMapper jsonMapper = new CustomDefaultJackson2JavaTypeMapper();
        setJavaTypeMapper(jsonMapper);

        if (getClassMapper() == null) {
            getJavaTypeMapper().fromJavaType(mapper.constructType(objectToConvert.getClass()),messageProperties);
            String type = NormalizeClass(mapper.constructType(objectToConvert.getClass()).getRawClass().getName());
            messageProperties.setType(type);
        }
        else {
            getClassMapper().fromClass(objectToConvert.getClass(),messageProperties);
            String type = NormalizeClass(objectToConvert.getClass().getName());
            messageProperties.setType(type);
        }
        return new Message(bytes, messageProperties);
    }

    private String NormalizeClass(String classToNormalizeMessage)
    {
        return classToNormalizeMessage.replace("DirectoryMessage.","DirectoryMessage:");
    }
}
