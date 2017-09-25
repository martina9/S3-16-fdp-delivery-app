package com.fastdeliveryservice.service;

import com.fastdeliveryservice.domain.ProductRestaurantDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Martina Gabellini
 */

@Service
public class ProductRestaurantService implements IProductRestaurantService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Environment environment;

    private RabbitTemplate rabbitTemplate;

    private DirectExchange directExchange;


    @Autowired
    public ProductRestaurantService(Environment environment,
                          RabbitTemplate rabbitTemplate,
                          DirectExchange directExchange) {
        this.environment = environment;
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }

     /* Consumes location list based on election query
     *
             * @param coordinateX
     * @param coordinateY
     * @return List of locations
     */

    public List<ProductRestaurantDto> getProductByRestaurantCode(String restaurantCode) {

        String restaurants = (String) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.DeliveryMessageService:Request.ProductRestaurantByRestaurantCode", restaurantCode);

        TypeReference<Map<String, List<ProductRestaurantDto>>> mapType = new TypeReference<Map<String, List<ProductRestaurantDto>>>() { };

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        Map<String, List<ProductRestaurantDto>> restaurantsMap = new HashMap<>();

        try {
            restaurantsMap = objectMapper.readValue(restaurants, mapType);
        }
        catch (IOException e) {
            logger.info(String.valueOf(e));
        }

        List<ProductRestaurantDto> restaurantsList = restaurantsMap.get("productRestaurant");

        if (logger.isDebugEnabled()) {
            logger.debug("List of {} locations received...", restaurantsList.size());
        }

        return restaurantsList;
    }

    @SuppressWarnings("unchecked")
    public List<ProductRestaurantDto> getProductsByRestaurant(int restaurantId) {

        String restaurants = (String) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.DeliveryMessageService:Request.ProductRestaurantByRestaurantId", restaurantId);

        TypeReference<Map<String, List<ProductRestaurantDto>>> mapType = new TypeReference<Map<String, List<ProductRestaurantDto>>>() { };

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        Map<String, List<ProductRestaurantDto>> restaurantsMap = new HashMap<>();

        try {
            restaurantsMap = objectMapper.readValue(restaurants, mapType);
        }
        catch (IOException e) {
            logger.info(String.valueOf(e));
        }

        List<ProductRestaurantDto> restaurantsList = restaurantsMap.get("productRestaurants");

        if (logger.isDebugEnabled()) {
            logger.debug("List of {} locations received...", restaurantsList.size());
        }

        return restaurantsList;
    }

    @Override
    public ProductRestaurantDto getProductRestaurantById(int id) {
        String productRestaurant = (String) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.DeliveryMessageService:Request.ProductRestaurant", id);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);

        ProductRestaurantDto productRestaurantDto = null;
        try {
            productRestaurantDto = objectMapper.readValue(productRestaurant, ProductRestaurantDto.class);
         }
        catch (IOException e) {
            logger.info(String.valueOf(e));
        }

        return productRestaurantDto;
    }

    @Override
    public synchronized Integer add(ProductRestaurantDto productRestaurantDto){
        Integer added = (Integer) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.DeliveryMessageService:Request.AddProductRestaurant", productRestaurantDto);

        if (logger.isDebugEnabled()) {
            logger.debug("Request Add Product Restaurant received...", added);
        }

        return added;
    }

    @Override
    public synchronized int update(ProductRestaurantDto productRestaurantDto) {
        int updatedId = (int) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.DeliveryMessageService:Request.UpdateProductRestaurant", productRestaurantDto);

        if (logger.isDebugEnabled()) {
            logger.debug("Request Update Product Restaurant received...", updatedId);
        }

        return updatedId;
    }

    @Override
    public synchronized int delete(int id) {
        int deletedId = (int) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.DeliveryMessageService:Request.DeleteProductRestaurant", id);

        if (logger.isDebugEnabled()) {
            logger.debug("Request Delete Product Restaurant received...", deletedId);
        }

        return deletedId;
    }
}