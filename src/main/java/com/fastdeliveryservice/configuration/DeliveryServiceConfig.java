package com.fastdeliveryservice.configuration;

import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.DefaultClassMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author  mGabellini
 */

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




//    // You can comment all methods below and remove interface's implementation to use the default serialization / deserialization
//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//        final RabbitTemplate rabbitTemplate = new RabbitTemplate(rabbitConnectionFactory);
//        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
//        rabbitTemplate.setExchange("easy_net_q_rpc");
//        rabbitTemplate.setReplyQueue(replyQueue());
//     //   rabbitTemplate.setReplyAddress("FDP.OrderService.MessageDirectory:Response.OrderInfo");
//        return rabbitTemplate;
//    }


//    @Bean
//        public SimpleMessageListenerContainer replyListenerContainer() {
//            SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//            container.setConnectionFactory(this.rabbitConnectionFactory);
//            container.setQueues(replyQueue());
//            container.setMessageListener(fixedReplyQRabbitTemplate());
//            return container;
//        }

    public static class PojoListener {

        public String handleMessage(String foo) {
            return foo.toUpperCase();
        }
    }
//        @Bean
//        public SimpleMessageListenerContainer serviceListenerContainer() {
//            SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//            container.setConnectionFactory(this.rabbitConnectionFactory);
//            container.setQueues(requestQueue());
//            container.setMessageListener(new MessageListenerAdapter(new PojoListener()));
//            return container;
////        }
//    @Bean
//    public Queue requestQueue() {
//        return new Queue("FDP.OrderService.MessageDirectory:Request.OrderInfo");
//    }
//
//    @Bean
//    public Queue replyQueue() {
//        return new Queue("RPC:response");
//    }

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
//
//    @Override
//    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
//        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
//    }

//    @Bean
//    public SimpleMessageListenerContainer replyListenerContainer() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(this.rabbitConnectionFactory);
//        container.setQueues(replyQueue());
//        container.setMessageListener(fixedReplyQRabbitTemplate());
//        return container;
//    }
//
//    @Bean
//    public SimpleMessageListenerContainer serviceListenerContainer() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(this.rabbitConnectionFactory);
//        container.setQueues(requestQueue());
//        container.setMessageListener(new MessageListenerAdapter(new PojoListener()));
//        return container;
//    }




}
