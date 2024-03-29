package com.fastdeliveryservice.configuration;

import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableRabbit
@EnableScheduling
public class DeliveryServiceConfig  {

    @Autowired
    public ConnectionFactory rabbitConnectionFactory;
    /**
     * Returns an Exchange for process sincronize message.
     *
     * @return the URL exchange
     * @see    DirectExchange
     */

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("rpc.exchange");
    }


    @Bean
    public RabbitTemplate fixedReplyQRabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(this.rabbitConnectionFactory);
        template.setMessageConverter(producerJackson2MessageConverter());
        template.setReceiveTimeout(10000);
        template.setReplyTimeout(10000);
        return template;
    }

    @Bean
    public CustomJackson2JsonMessageConverter producerJackson2MessageConverter() {
        CustomDefaultJackson2JavaTypeMapper mapper = new CustomDefaultJackson2JavaTypeMapper();
        CustomJackson2JsonMessageConverter json = new CustomJackson2JsonMessageConverter();
        json.setJavaTypeMapper(mapper);

        return json;
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultClassMapper classMapper() {
        DefaultClassMapper classMapper = new CustomDefaultClassMapper();
        return classMapper;
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        return factory;
    }
}
