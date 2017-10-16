package com.fastdeliveryservice.service;

import FDP.ProductService.MessageDirectory.Request.RestaurantList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Martina
 */

@Service
public class RestaurantService implements IRestaurantService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Environment environment;

    private RabbitTemplate rabbitTemplate;

    private DirectExchange directExchange;

    @Autowired
    public RestaurantService(Environment environment, RabbitTemplate rabbitTemplate, DirectExchange directExchange)
    {
        this.environment = environment;
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }

    /**
     * Produces query message containing election
     * Consumes location list based on election query
     *
     * @param coordinateX
     * @param coordinateY
     * @return List of locations
     */
     public FDP.ProductService.MessageDirectory.Response.RestaurantList getRestaurantByCoordinates(double coordinateX, double coordinateY) {
        logger.debug("Sending RPC request message for list of locations...");

        throw new NotImplementedException();
}

    /**
     * Produces message containing city search of Restaurant
     *
     * @param city
     * @return List of Restaurant
     */

    public FDP.ProductService.MessageDirectory.Response.RestaurantList getRestaurantList(String city, Integer id) {

        RestaurantList request = new RestaurantList();
        if(city != null){
            request.setCity(city);
        }
        if(id != null){
            request.setId(id.intValue());
        }

        logger.debug("Sending RPC request message for list of locations...");
        FDP.ProductService.MessageDirectory.Response.RestaurantList restaurantsList = (FDP.ProductService.MessageDirectory.Response.RestaurantList) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.ProductService.MessageDirectory:Request.RestaurantList", request);
        return restaurantsList;
    }
}