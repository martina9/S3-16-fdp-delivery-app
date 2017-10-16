package com.fastdeliveryservice.service;

import FDP.ProductService.MessageDirectory.Request.AddProductRestaurant;
import FDP.ProductService.MessageDirectory.Request.DeleteProductRestaurant;
import FDP.ProductService.MessageDirectory.Request.ProductRestaurantList;
import FDP.ProductService.MessageDirectory.Request.UpdateProductRestaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

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

    public FDP.ProductService.MessageDirectory.Response.ProductRestaurantList getProductsByRestaurantId(int restaurantId) {
        logger.debug("Sending RPC request message for getting order...");

        ProductRestaurantList request = new ProductRestaurantList();
        request.setRestaurantId(restaurantId);
        FDP.ProductService.MessageDirectory.Response.ProductRestaurantList productRestaurantList = (FDP.ProductService.MessageDirectory.Response.ProductRestaurantList) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.ProductService.MessageDirectory:Request.ProductRestaurantList", request);
        return productRestaurantList;
    }


    /**
     * Produces message request containing product id
     *
     * @param id
     * @return Product by Id
     */

    public FDP.ProductService.MessageDirectory.Response.ProductRestaurantInfo getProductRestaurantById(int id) {
        logger.debug("Sending RPC request message for getting order...");
        FDP.ProductService.MessageDirectory.Request.ProductRestaurantInfo info = new FDP.ProductService.MessageDirectory.Request.ProductRestaurantInfo();
        info.setId(id);
        FDP.ProductService.MessageDirectory.Response.ProductRestaurantInfo productInfo = ( FDP.ProductService.MessageDirectory.Response.ProductRestaurantInfo) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.ProductService.MessageDirectory:Request.ProductRestaurantInfo",info);
        return productInfo;
    }

    public synchronized int add(String name, int restaurantId, double price, int productId){
        logger.debug("Sending RPC request message for getting order...");

        AddProductRestaurant addProductRestaurant = new AddProductRestaurant();
        addProductRestaurant.setName(name);
        addProductRestaurant.setPrice(price);
        addProductRestaurant.setProductId(productId);
        addProductRestaurant.setRestaurantId(restaurantId);

        FDP.ProductService.MessageDirectory.Response.AddProductRestaurant resultProduct = (FDP.ProductService.MessageDirectory.Response.AddProductRestaurant) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.ProductService.MessageDirectory:Request.AddProductRestaurant", addProductRestaurant);

        if (logger.isDebugEnabled()) {
            logger.debug("Product received...", resultProduct.getId());
        }

        return resultProduct.getId();
    }


    public synchronized int update(int id , String name, int restaurantId, double price, int productId){
        logger.debug("Sending RPC request message for getting order...");


        UpdateProductRestaurant updateProductRestaurant = new UpdateProductRestaurant();
        updateProductRestaurant.setId(id);
        updateProductRestaurant.setName(name);
        updateProductRestaurant.setPrice(price);
        updateProductRestaurant.setProductId(productId);
        updateProductRestaurant.setRestaurantId(restaurantId);

        FDP.ProductService.MessageDirectory.Response.UpdateProductRestaurant resultProduct = (FDP.ProductService.MessageDirectory.Response.UpdateProductRestaurant) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.ProductService.MessageDirectory:Request.UpdateProductRestaurant", updateProductRestaurant);

        if (logger.isDebugEnabled()) {
            logger.debug("Product received...", resultProduct.getId());
        }

        return resultProduct.getId();
    }

    public synchronized int delete(int id){
        logger.debug("Sending RPC request message for getting order...");

        DeleteProductRestaurant request = new DeleteProductRestaurant();
        request.setId(id);
        FDP.ProductService.MessageDirectory.Response.DeleteProductRestaurant resultProduct = (FDP.ProductService.MessageDirectory.Response.DeleteProductRestaurant) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.ProductService.MessageDirectory:Request.DeleteProductRestaurant", request);

        if (logger.isDebugEnabled()) {
            logger.debug("Product received...", resultProduct.getId());
        }

        return resultProduct.getId();
    }
}