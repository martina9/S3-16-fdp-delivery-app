package com.fastdeliveryservice.configuration;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.SimpleMessageConverter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class CustomJackson2JsonMessageConverter extends Jackson2JsonMessageConverter {

    public static final String DEFAULT_CHARSET = "UTF-8";

    private Object convertBytesToObject(byte[] body, String encoding, JavaType targetJavaType) throws IOException {
        String contentAsString = new String(body, encoding);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(contentAsString, targetJavaType);
    }

    private Object convertBytesToObject(byte[] body, String encoding, Class<?> targetClass) throws IOException {
        String contentAsString = new String(body, encoding);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(contentAsString, mapper.constructType(targetClass));
    }

   @Override
    public Object fromMessage(Message message)  throws MessageConversionException {
        Object content = null;

        CustomDefaultJackson2JavaTypeMapper jsonMapper = new CustomDefaultJackson2JavaTypeMapper();

        MessageProperties properties = message.getMessageProperties();
        String fullType = null;
        for (Map.Entry<String, Object> entry : message.getMessageProperties().getHeaders().entrySet()) {
            String key = entry.getKey();
            if(key.equals("message_type")) {
                Object value = entry.getValue();
                fullType = (String) value;
                String classType = fullType.split(",")[0];
                properties.setType(classType);
            }
        }

        String contentType = null;

        if (properties != null) {
            if(properties.getContentType() != null) {
                if(message.getMessageProperties() != null && message.getMessageProperties().getContentType() != null)
                {
                    contentType = message.getMessageProperties().getContentType();
                }
                //properties.getContentType();
            }
            else {
                contentType = "application/json";
            }
            if (contentType != null && contentType.contains("json")) {
                String encoding = properties.getContentEncoding();
                if (encoding == null) {
                    encoding = getDefaultCharset();
                }
                try {

                    if (getClassMapper() == null) {
                        JavaType targetJavaType = jsonMapper
                                .toJavaType(message.getMessageProperties());
                        content = convertBytesToObject(message.getBody(),                                encoding, targetJavaType);
                    }
                    else {
                        Class<?> targetClass = jsonMapper.toClass(
                                message.getMessageProperties());
                        content = convertBytesToObject(message.getBody(),
                                encoding, targetClass);
                    }
                }
                catch (IOException e) {
                    throw new MessageConversionException(
                            "Failed to convert Message content", e);
                }
            }
            else if (contentType != null && contentType.startsWith("text")) {
                String encoding = properties.getContentEncoding();
                if (encoding == null) {
                    encoding = getDefaultCharset();
                }
                try {
                    content = new String(message.getBody(), encoding);
                }
                catch (UnsupportedEncodingException e) {
                    throw new MessageConversionException(
                            "failed to convert text-based Message content", e);
                }
            }
            else {
//                if (log.isWarnEnabled()) {
//                    log.warn("Could not convert incoming message with content-type ["
//                            + contentType + "]");
                }
            }
        if (content == null) {

            content = message.getBody();
        }
        return content;
    }
    @Override
    protected Message createMessage(Object objectToConvert, MessageProperties messageProperties)
            throws MessageConversionException {
        ObjectMapper mapper = new ObjectMapper();
         byte[] bytes;
        byte[] bytes1;
        String jsonString1;
        try
        {
            String jsonString = mapper
                    .writeValueAsString(objectToConvert);
            bytes = jsonString.getBytes(getDefaultCharset());

             jsonString1 = mapper.writeValueAsString(UUID.randomUUID().toString()).replace("\"","");
            bytes1 = jsonString1.getBytes(getDefaultCharset());
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

        messageProperties.setCorrelationId(bytes1);
        //messageProperties.setCorrelationIdString(UUID.randomUUID().toString());
        messageProperties.setHeader("approx_retry","0");
        messageProperties.setHeader("x-death","0");
        messageProperties.setHeader("approx_retry","2");
        messageProperties.setHeader("retry_count","0");
        messageProperties.setHeader("message_context","{\"GlobalRequestId\":\""+java.util.UUID.randomUUID()+"\"}");
        messageProperties.setHeader("exception","");
//
//        public static readonly string MessageType = "message_type";
//        public static readonly string Sent = "sent";
//        public static readonly string ApproximateRetry = "approx_retry";
//        public static readonly string Death = "x-death";
//        public static readonly string EstimatedRetry = "approx_retry";
//        public static readonly string RetryCount = "retry_count";
//        public static readonly string Context = "message_context";
//        public static readonly string ExceptionHeader = "exception";

        messageProperties.setMessageId(UUID.randomUUID().toString());
        messageProperties.setContentType("application/json");

        return new Message(bytes, messageProperties);
    }

    private String NormalizeClass(String classToNormalizeMessage)
    {
        return classToNormalizeMessage;
       // return classToNormalizeMessage.replace("MessageDirectory.","MessageDirectory:");
    }
}
