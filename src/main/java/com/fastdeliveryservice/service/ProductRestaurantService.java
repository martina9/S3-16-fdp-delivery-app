package com.fastdeliveryservice.service;

/**
 * Created by Martina
 */

import FDP.ProductService.MessageDirectory.Response.*;

public interface ProductRestaurantService {

    ProductRestaurantList getProductsByRestaurantId(int idRestaurant);
    ProductRestaurantInfo getProductRestaurantById(int id);
    int add(String name, int restaurantId, double price, int productId);
    int update(int id , String name, int restaurantId, double price, int productId);
    int delete(int id);
}