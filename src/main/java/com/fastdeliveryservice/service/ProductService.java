package com.fastdeliveryservice.service;

import FDP.ProductService.MessageDirectory.Request.*;
import FDP.ProductService.Shared.CategoryInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Martina
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 */

@Service
public class ProductService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private RabbitTemplate rabbitTemplate;

    private DirectExchange directExchange;


    @Autowired
    public ProductService(
                           RabbitTemplate rabbitTemplate,
                           DirectExchange directExchange)
    {
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }

    public List<CategoryInfo> getAllCategories() {
        logger.debug("Sending RPC request message for getting order...");
 
        FDP.ProductService.MessageDirectory.Response.CategoryList categoryList = (FDP.ProductService.MessageDirectory.Response.CategoryList) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.ProductService.MessageDirectory:Request.CategoryList", new CategoryList());
        return categoryList.getItems();
    }

    public List<FDP.ProductService.MessageDirectory.Response.ProductInfo> getAllProducts() {
        logger.debug("Sending RPC request message for getting order...");

        FDP.ProductService.MessageDirectory.Response.ProductList productList = (FDP.ProductService.MessageDirectory.Response.ProductList) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.ProductService.MessageDirectory:Request.ProductList", new ProductList());
        return productList.getItems();
    }

    /**
     * Produces message request containing product id
     *
     * @param id
     * @return Product by Id
     */

    public FDP.ProductService.MessageDirectory.Response.ProductInfo getProductById(int id) {
        logger.debug("Sending RPC request message for getting order...");
        ProductInfo info = new ProductInfo();
        info.setId(id);
        FDP.ProductService.MessageDirectory.Response.ProductInfo productInfo = ( FDP.ProductService.MessageDirectory.Response.ProductInfo) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.ProductService.MessageDirectory:Request.Product",info);
        return productInfo;
    }

    public synchronized FDP.ProductService.MessageDirectory.Response.AddProduct addProduct(AddProduct addProduct){
        logger.debug("Sending RPC request message for getting order...");

            FDP.ProductService.MessageDirectory.Response.AddProduct resultProduct = (FDP.ProductService.MessageDirectory.Response.AddProduct) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.ProductService.MessageDirectory:Request.AddProduct", addProduct);

            if (logger.isDebugEnabled()) {
                logger.debug("Product received...", resultProduct.getId());
            }

            return resultProduct;
    }

    public synchronized FDP.ProductService.MessageDirectory.Response.UpdateProduct updateProduct(UpdateProduct updateProduct){
        logger.debug("Sending RPC request message for getting order...");

        FDP.ProductService.MessageDirectory.Response.UpdateProduct resultProduct = (FDP.ProductService.MessageDirectory.Response.UpdateProduct) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.ProductService.MessageDirectory:Request.UpdateProduct", updateProduct);

        if (logger.isDebugEnabled()) {
            logger.debug("Product received...", resultProduct.getId());
        }

        return resultProduct;
    }

    public synchronized FDP.ProductService.MessageDirectory.Response.DeleteProduct deleteProduct(DeleteProduct deleteProduct){
        logger.debug("Sending RPC request message for getting order...");

        FDP.ProductService.MessageDirectory.Response.DeleteProduct resultProduct = (FDP.ProductService.MessageDirectory.Response.DeleteProduct) rabbitTemplate.convertSendAndReceive(directExchange.getName(), "FDP.ProductService.MessageDirectory:Request.DeleteProduct", deleteProduct);

        if (logger.isDebugEnabled()) {
            logger.debug("Product received...", resultProduct.getId());
        }

        return resultProduct;
    }

}