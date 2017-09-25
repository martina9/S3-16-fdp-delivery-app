package com.fastdeliveryservice.service;

/**
 * Created by Martina
 */

import com.fastdeliveryservice.domain.ProductRestaurantDto;

import java.util.List;

public interface IProductRestaurantService {

    List<ProductRestaurantDto> getProductsByRestaurant(int idRestaurant);
    ProductRestaurantDto getProductRestaurantById(int id);
    Integer add(ProductRestaurantDto productRestaurantDto);
    int update(ProductRestaurantDto productRestaurantDto);
    int delete(int id);
}