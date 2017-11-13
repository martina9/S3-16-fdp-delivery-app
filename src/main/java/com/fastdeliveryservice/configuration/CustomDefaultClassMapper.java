package com.fastdeliveryservice.configuration;

import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.context.annotation.Configuration;


    @Configuration
    public class CustomDefaultClassMapper extends DefaultClassMapper {

    @Override
    public String getClassIdFieldName() {
        return "type";
    }

}
