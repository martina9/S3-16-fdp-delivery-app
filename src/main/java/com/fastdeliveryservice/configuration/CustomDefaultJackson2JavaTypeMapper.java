package com.fastdeliveryservice.configuration;

import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Martina on 27/09/2017.
 */
    @Configuration
    public class CustomDefaultJackson2JavaTypeMapper extends DefaultJackson2JavaTypeMapper {

    @Override
    public String getClassIdFieldName() {
        return "type";
    }

}
