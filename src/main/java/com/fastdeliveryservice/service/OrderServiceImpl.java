package com.fastdeliveryservice.service;

import FDP.OrderService.MessageDirectory.Response.ConfirmOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Martina
 */

@Service
public class OrderServiceImpl implements OrderService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Environment environment;

    private RabbitTemplate rabbitTemplate;

    private DirectExchange directExchange;

    @Autowired
    public OrderServiceImpl(Environment environment, RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
        this.environment = environment;
        this.directExchange = directExchange;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Produces message request containing userId
     *
     * @param userId
     * @return List of orders
     */

    @SuppressWarnings("unchecked")
    public FDP.OrderService.MessageDirectory.Response.OrderList GetOrdersList(Integer userId, Integer restaurantId) {
        logger.debug("Sending RPC request message for getting order...");
        FDP.OrderService.MessageDirectory.Request.OrderList request = new FDP.OrderService.MessageDirectory.Request.OrderList();
        request.setUserId(userId);
        request.setRestaurantId(restaurantId);
        FDP.OrderService.MessageDirectory.Response.OrderList productList = (FDP.OrderService.MessageDirectory.Response.OrderList) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.OrderService.MessageDirectory:Request.OrderList.#", request);
        return productList;
    }

    /**
     * Produces message request containing order id
     *
     * @param orderId
     * @return Order by Id
     */

    @SuppressWarnings("unchecked")
    public FDP.OrderService.MessageDirectory.Response.OrderInfo GetOrderById(Integer orderId) throws Exception {
        logger.debug("Sending RPC request message for getting order...");

        if (orderId == null) {
            Exception ex  = new Exception("Required Exception.GetOrderById param OrderId is missing");
            throw ex;
        }
        FDP.OrderService.MessageDirectory.Request.OrderInfo request = new FDP.OrderService.MessageDirectory.Request.OrderInfo();
        request.setId(orderId.intValue());
        FDP.OrderService.MessageDirectory.Response.OrderInfo result = (FDP.OrderService.MessageDirectory.Response.OrderInfo) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.OrderService.MessageDirectory:Request.OrderInfo", request);
        return result;
    }

    public int add(int userId, double amount, String deliveryType, String address, String city, String phoneNumber, String email, int restaurantId) {

        FDP.OrderService.MessageDirectory.Request.ConfirmOrder request = new FDP.OrderService.MessageDirectory.Request.ConfirmOrder(userId,amount,deliveryType,address,city,phoneNumber,email,restaurantId);
        ConfirmOrder result = (ConfirmOrder) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.OrderService.MessageDirectory:Request.ConfirmOrder", request);

        if (logger.isDebugEnabled()) {
            logger.debug("Order received...", result.getId());
        }

        return result.getId();
    }


}
