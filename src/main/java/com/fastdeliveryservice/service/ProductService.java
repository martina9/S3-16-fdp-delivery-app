package com.fastdeliveryservice.service;

import com.fastdeliveryservice.domain.ProductDto;

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
 * Created by Martina
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 */

@Service
public class ProductService implements IProductService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Environment environment;

    private RabbitTemplate rabbitTemplate;

    private DirectExchange directExchange;


    @Autowired
    public ProductService(Environment environment,
                           RabbitTemplate rabbitTemplate,
                           DirectExchange directExchange)
    {
        this.environment = environment;
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }

    @Override
    public List<ProductDto> getAllProductsByRestaurant(int idRestaurant) {
        logger.debug("Sending RPC request message for getting order...");

        String order = (String) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.DeliveryMessageService:Request.ProductList", idRestaurant);

        TypeReference<Map<String, List<ProductDto>>> mapType = new TypeReference<Map<String, List<ProductDto>>>() {
        };

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        Map<String, List<ProductDto>> restaurantsMap = new HashMap<>();

        try {
            restaurantsMap = objectMapper.readValue(order, mapType);
        } catch (IOException e) {
            logger.info(String.valueOf(e));
        }

        List<ProductDto> productDto = restaurantsMap.get("product");

        if (logger.isDebugEnabled()) {
            logger.debug("Product received...", productDto.size());
        }

        return productDto;
    }

    /**
     * Produces message request containing product id
     *
     * @param id
     * @return Product by Id
     */
    public ProductDto getProductById(int id) {
        logger.debug("Sending RPC request message for getting order...");

        String order = (String) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.DeliveryMessageService:Request.Product", id);

        TypeReference<Map<String, ProductDto>> mapType = new TypeReference<Map<String, ProductDto>>() {
        };

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        Map<String, ProductDto> restaurantsMap = new HashMap<>();

        try {
            restaurantsMap = objectMapper.readValue(order, mapType);
        } catch (IOException e) {
            logger.info(String.valueOf(e));
        }

        ProductDto productDto = restaurantsMap.get("product");

        if (logger.isDebugEnabled()) {
            logger.debug("Product received...", productDto.getId());
        }

        return productDto;
    }
    public synchronized int add(ProductDto productDto){
        logger.debug("Sending RPC request message for getting order...");
        int resultProductId = (int) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.DeliveryMessageService:Request.AddProduct", productDto);

        if (logger.isDebugEnabled()) {
            logger.debug("Product received...", resultProductId);
        }

        return resultProductId;
    }

    @SuppressWarnings("unchecked")
    public int update(ProductDto product) {
        logger.debug("Sending RPC request message for getting product...");

        int resultProductId = (int) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.DeliveryMessageService:Request.UpdateProduct", product);

        if (logger.isDebugEnabled()) {
            logger.debug("Product received...", resultProductId);
        }

        return resultProductId;
    }

    @SuppressWarnings("unchecked")
    public int delete(int productId) {
        int id = (int) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.DeliveryMessageService:Request.DeleteProduct", productId);

        if (logger.isDebugEnabled()) {
            logger.debug("Request Delete Product received...", productId);
        }
        return id;
    }
}