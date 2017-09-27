package com.fastdeliveryservice.service;

import com.fastdeliveryservice.domain.OrderDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import FDP.OrderService.DirectoryMessage.Request.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Martina
 */

@Service
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Environment environment;

    private RabbitTemplate rabbitTemplate;

    private DirectExchange directExchange;

    @Autowired
    public OrderService(Environment environment, RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
        this.environment = environment;
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }


    private class OrderList implements Serializable
    {
        public OrderList()
        {
            UserId= 1;
        }
        public int UserId;
    }

    /**
     * Produces message request containing userId
     *
     * @param userId
     * @return List of orders
     */

    @SuppressWarnings("unchecked")
    public List<OrderDto> getOrderByUserId(int userId) {

        OrderInfo info = new OrderInfo(1);
        ObjectMapper mapperObj = new ObjectMapper();

        try {
            // get Employee object as a json string
            String jsonStr = mapperObj.writeValueAsString("");
            System.out.println(jsonStr);


        //Should be Done Sanity Check
        logger.debug("Sending RPC request message for list of orders...");

        String orders = (String) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.OrderService.DirectoryMessage:Request.OrderList", info);

        TypeReference<Map<String, List<OrderDto>>> mapType = new TypeReference<Map<String, List<OrderDto>>>() {};

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        Map<String, List<OrderDto>> orderMap = new HashMap<>();

        try {
            orderMap = objectMapper.readValue(orders, mapType);
        } catch (IOException e) {
            logger.info(String.valueOf(e));
        }

        List<OrderDto> orderLists = orderMap.get("Items");

        if (logger.isDebugEnabled()) {
            logger.debug("List of {} locations received...", orderLists.size());
        }

        return orderLists;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Produces message request containing order id
     *
     * @param id
     * @return Order by Id
     */

    @SuppressWarnings("unchecked")
    public OrderDto getOrderById(int id) {
        logger.debug("Sending RPC request message for getting order...");

        OrderInfo orderInfo = new OrderInfo(1);

        String order = (String) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.OrderService.DirectoryMessage:Request.OrderInfo", orderInfo);

        TypeReference<Map<String, OrderDto>> mapType = new TypeReference<Map<String, OrderDto>>() {
        };

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        Map<String, OrderDto> restaurantsMap = new HashMap<>();

        try {
            restaurantsMap = objectMapper.readValue(order, mapType);
        } catch (IOException e) {
            logger.info(String.valueOf(e));
        }

        OrderDto orderDto = restaurantsMap.get("order");

        if (logger.isDebugEnabled()) {
            logger.debug("Order received...", orderDto.getId());
        }

        return orderDto;
    }


    @SuppressWarnings("unchecked")
    public boolean update(OrderDto order) {
        logger.debug("Sending RPC request message for getting order...");

        boolean resultOrderId = (boolean) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.DeliveryMessageService:Request.UpdateOrder", order);

        if (logger.isDebugEnabled()) {
            logger.debug("Order received...", resultOrderId);
        }

        return resultOrderId;
    }

    @SuppressWarnings("unchecked")
    public boolean add(OrderDto order) {

        logger.debug("Sending RPC request message for getting order...");
        boolean resultOrderId = (boolean) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.DeliveryMessageService:Request.AddOrder", order);

        if (logger.isDebugEnabled()) {
            logger.debug("Order received...", resultOrderId);
        }

        return resultOrderId;
    }


}
