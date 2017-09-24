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
    boolean update(ProductRestaurantDto productRestaurantDto);
    boolean delete(int id);
}