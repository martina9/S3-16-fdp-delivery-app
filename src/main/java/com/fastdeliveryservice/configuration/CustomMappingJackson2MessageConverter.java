package com.fastdeliveryservice.configuration;

import com.fasterxml.jackson.databind.JavaType;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConversionException;

import java.io.IOException;

/**
 * Created by Martina on 27/09/2017.
 */
    @Configuration
    public class CustomMappingJackson2MessageConverter extends MappingJackson2MessageConverter {

    @Override
    protected Object convertFromInternal(Message<?> message, Class<?> targetClass, Object conversionHint) {
        JavaType javaType = super.getObjectMapper().constructType(targetClass);
        Object payload = message.getPayload();
        Class<?> view = getSerializationView(conversionHint);
        // Note: in the view case, calling withType instead of forType for compatibility with Jackson <2.5
        try {
            if (payload instanceof byte[]) {
                if (view != null) {
                    return super.getObjectMapper().readerWithView(view).forType(javaType).readValue((byte[]) payload);
                }
                else {
                    return super.getObjectMapper().readValue((byte[]) payload, javaType);
                }
            }
            else {
                if (view != null) {
                    return super.getObjectMapper().readerWithView(view).forType(javaType).readValue(payload.toString());
                }
                else {
                    return super.getObjectMapper().readValue(payload.toString(), javaType);
                }
            }
        }
        catch (IOException ex) {
            throw new MessageConversionException(message, "Could not read JSON: " + ex.getMessage(), ex);
        }
    }

}
