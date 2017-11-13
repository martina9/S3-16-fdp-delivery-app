package com.fastdeliveryservice.service;

/**
 * Created by Martina
 */

public interface RestaurantService {
    FDP.ProductService.MessageDirectory.Response.RestaurantList getRestaurantList(String city, Integer id);
    FDP.ProductService.MessageDirectory.Response.RestaurantList getRestaurantByCoordinates(double coordinateX, double coordinateY);
}