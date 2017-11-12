package com.fastdeliveryservice.service;

/**
 * Created by Martina
 */

import FDP.ProductService.MessageDirectory.Request.AddProduct;
import FDP.ProductService.MessageDirectory.Request.UpdateProduct;

import java.util.List;

public interface ProductService {
    List<FDP.ProductService.MessageDirectory.Response.ProductInfo> getAllProducts();
    FDP.ProductService.MessageDirectory.Response.ProductInfo getProductById(int productId);
    int add(AddProduct product);
    int update(UpdateProduct product);
    int delete(int productId);
}