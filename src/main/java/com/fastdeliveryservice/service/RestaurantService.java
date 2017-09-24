package com.fastdeliveryservice.service;

import com.fastdeliveryservice.domain.RestaurantDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Martina
 */

@Service
public class RestaurantService {

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

    private RestTemplate restTemplate() {
        // reference: http://izeye.blogspot.com/2015/01/consume-spring-data-rest-hateoas-hal.html
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new Jackson2HalModule());

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
        converter.setObjectMapper(mapper);
        return new RestTemplate(Arrays.asList(converter));
    }

    /**
     * Produces query message containing election
     * Consumes location list based on election query
     *
     * @param coordinateX
     * @param coordinateY
     * @return List of locations
     */

    @SuppressWarnings("unchecked")
    public List<RestaurantDto> getRestaurantMessageRpc(double coordinateX, double coordinateY) {
        logger.debug("Sending RPC request message for list of locations...");

        CoordinateHelperMessage coordinateMessage = new CoordinateHelperMessage();
        coordinateMessage.setCoordinateY(coordinateY);
        coordinateMessage.setCoordinateX(coordinateX);
        String locations = (String) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "rpc", coordinateMessage);

        TypeReference<Map<String, List<RestaurantDto>>> mapType = new TypeReference<Map<String, List<RestaurantDto>>>() { };

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        Map<String, List<RestaurantDto>> locationsMap = new HashMap<>();

        try {
            locationsMap = objectMapper.readValue(locations, mapType);
        } catch (IOException e) {
            logger.info(String.valueOf(e));
        }

        List<RestaurantDto> locationsList = locationsMap.get("locations");

        if (logger.isDebugEnabled()) {
            logger.debug("List of {} locations received...", locationsList.size());
        }

        return locationsList;
    }

    /**
     * Produces message containing city search of Restaurant
     *
     * @param city
     * @return List of Restaurant
     */

    @SuppressWarnings("unchecked")
    public List<RestaurantDto> getRestaurantMessageRpc(String city) {
        logger.debug("Sending RPC request message for list of locations...");

        String restaurants = (String) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.DeliveryMessageService:Request.RestaurantList", city);

        TypeReference<Map<String, List<RestaurantDto>>> mapType = new TypeReference<Map<String, List<RestaurantDto>>>() { };

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        Map<String, List<RestaurantDto>> restaurantsMap = new HashMap<>();

        try {
            restaurantsMap = objectMapper.readValue(restaurants, mapType);
        }
        catch (IOException e) {
            logger.info(String.valueOf(e));
        }

        List<RestaurantDto> restaurantsList = restaurantsMap.get("restaurants");

        if (logger.isDebugEnabled()) {
            logger.debug("List of {} locations received...", restaurantsList.size());
        }

        return restaurantsList;
    }

    private class CoordinateHelperMessage
    {
        private double coordinateX;

        private double coordinateY;

        public double getCoordinateX() {
            return coordinateX;
        }

        public void setCoordinateX(double coordinateX) {
            this.coordinateX = coordinateX;
        }

        public double getCoordinateY() {
            return coordinateY;
        }

        public void setCoordinateY(double coordinateY) {
            this.coordinateY = coordinateY;
        }
    }
}