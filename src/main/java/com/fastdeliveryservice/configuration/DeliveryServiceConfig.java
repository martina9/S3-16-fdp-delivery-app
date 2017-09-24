package com.fastdeliveryservice.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author  mGabellini
 */

@Configuration
public class DeliveryServiceConfig {

    /**
     * Returns an Exchange for process sincronize message.
     *
     * @return the URL exchange
     * @see    DirectExchange
     */

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("deliveryService.rpc");
    }

    /* Product Restaurant*/

    /**
     * Returns an Queue for process message about request ProductRestaurantById.
     *
     * @return the URL Queue
     * @see    Queue
     */

    @Bean
    public Queue queueProductRestaurantById() {
        return new Queue("FDP.DeliveryMessageService:Request.ProductRestaurant");
    }



    /**
     * Returns an Binding for process message about request ProductRestaurantByRestaurantId.
     *
     * @param exchange
     * @param queue
     * @return the URL Binding
     * @see    Binding
     */

    @Bean
    public Binding bindingProductRestaurantById(DirectExchange exchange, @Qualifier("queueProductRestaurantById") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("FDP.DeliveryMessageService:Request.ProductRestaurant");
    }


    /**
     * Returns an Queue for process message about request ProductRestaurantByRestaurantId.
     *
     * @return the URL Queue
     * @see    Queue
     */

    @Bean
    public Queue queueProductRestaurantByRestaurantId() {
        return new Queue("FDP.DeliveryMessageService:Request.ProductRestaurantByRestaurantId");
    }

    /**
     * Returns an Binding for process message about request ProductRestaurantByRestaurantId.
     *
     * @param exchange
     * @param queue
     * @return the URL Binding
     * @see    Binding
     */

    @Bean
    public Binding bindingProductRestaurant(DirectExchange exchange, @Qualifier("queueProductRestaurantByRestaurantId") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("FDP.DeliveryMessageService:Request.ProductRestaurantByRestaurantId");
    }




    /**
     * Returns an Queue for process message about request ProductRestaurantByRestaurantId.
     *
     * @return the URL Queue
     * @see    Queue
     */

    @Bean
    public Queue queueProductRestaurantByRestaurantCode() {
        return new Queue("FDP.DeliveryMessageService:Request.ProductRestaurantByRestaurantCode");
    }

    /**
     * Returns an Binding for process message about request ProductRestaurantByRestaurantCode.
     *
     * @param exchange
     * @param queue
     * @return the URL Binding
     * @see    Binding
     */

    @Bean
    public Binding bindingProductRestaurantByRestaurantCode(DirectExchange exchange, @Qualifier("queueProductRestaurantByRestaurantCode") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("FDP.DeliveryMessageService:Request.ProductRestaurantByRestaurantCode");
    }



    /**
     * Returns an Queue for process message about request AddProductRestaurant.
     *
     * @return the URL Queue
     * @see    Queue
     */

    @Bean
    public Queue queueAddProductRestaurant() {
        return new Queue("FDP.DeliveryMessageService:Request.AddProductRestaurant");
    }

    /**
     * Returns an Binding for process message about request AddProductRestaurant.
     *
     * @param exchange
     * @param queue
     * @return the URL Binding
     * @see    Binding
     */

    @Bean
    public Binding bindingAddProductRestaurant(DirectExchange exchange, @Qualifier("queueAddProductRestaurant") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("FDP.DeliveryMessageService:Request.AddProductRestaurant");
    }

    /**
     * Returns an Queue for process message about request UpdateProductRestaurant.
     *
     * @return the URL Queue
     * @see    Queue
     */

    @Bean
    public Queue queueUpdateProductRestaurant() {
        return new Queue("FDP.DeliveryMessageService:Request.UpdateProductRestaurant");
    }

    /**
     * Returns an Binding for process message about request UpdateProductRestaurant.
     *
     * @param exchange
     * @param queue
     * @return the URL Binding
     * @see    Binding
     */

    @Bean
    public Binding bindingUpdateProductRestaurant(DirectExchange exchange, @Qualifier("queueUpdateProductRestaurant") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("FDP.DeliveryMessageService:Request.UpdateProductRestaurant");
    }

    /**
     * Returns an Queue for process message about request DeleteProductRestaurant.
     *
     * @return the URL Queue
     * @see    Queue
     */

    @Bean
    public Queue queueDeleteProductRestaurant() {
        return new Queue("FDP.DeliveryMessageService:Request.DeleteProductRestaurant");
    }

    /**
     * Returns an Binding for process message about request DeleteProductRestaurant.
     *
     * @param exchange
     * @param queue
     * @return the URL Binding
     * @see    Binding
     */

    @Bean
    public Binding bindingDeleteProductRestaurant(DirectExchange exchange, @Qualifier("queueDeleteProductRestaurant") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("FDP.DeliveryMessageService:Request.DeleteProductRestaurant");
    }

    /* End product Restaurant*/

    @Bean
    public Queue QueueRestaurantList() {
        return new Queue("FDP.DeliveryMessageService:Request.RestaurantList");
    }

    @Bean
    public Binding bindingRestaurantList(DirectExchange exchange, @Qualifier("QueueRestaurantList") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("FDP.DeliveryMessageService:Request.RestaurantList");
    }

    @Bean
    public Queue QueueOrderList() {
        return new Queue("FDP.DeliveryMessageService:Request.OrderList");
    }

    @Bean
    public Queue QueueOrder() {
        return new Queue("FDP.DeliveryMessageService:Request.Order");
    }

    @Bean
    public Queue QueueAddOrder() {
        return new Queue("FDP.DeliveryMessageService:Request.AddOrder");
    }

    @Bean
    public Queue QueueUpdateOrder() {
        return new Queue("FDP.DeliveryMessageService:Request.UpdateOrder");
    }

    @Bean
    public Binding bindingOrderList(DirectExchange exchange, @Qualifier("QueueOrderList") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("FDP.DeliveryMessageService:Request.OrderList");
    }

    @Bean
    public Binding bindingOrder(DirectExchange exchange, @Qualifier("QueueOrder") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("FDP.DeliveryMessageService:Request.Order");
    }

    @Bean
    public Binding bindingAddOrder(DirectExchange exchange, @Qualifier("QueueAddOrder") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("FDP.DeliveryMessageService:Request.AddOrder");
    }

    @Bean
    public Binding bindingUpdateOrder(DirectExchange exchange, @Qualifier("QueueUpdateOrder") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("FDP.DeliveryMessageService:Request.UpdateOrder");
    }
}